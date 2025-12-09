package {{ cookiecutter.pkg_name }}.lib.utils.paging

import {{ cookiecutter.pkg_name }}.lib.utils.state.RemoteState
import {{ cookiecutter.pkg_name }}.lib.utils.state.RemoteStateData
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * The pagination implementation to use inside a business-logic module.
 *
 * Here, you pass a specific [PagingDataSource] to load data,
 * the [isSameItem] comparator, and two listeners. Optionally, you can pass a pre-defined
 * data, if any.
 */
class Pagination<T>(
    private val dataSource: PagingDataSource<T>,
    private val isSameItem: ((T, T) -> Boolean) = { a, b -> a == b },
    private val nextPageListener: (Result<List<T>>) -> Unit = {},
    private val refreshListener: (Result<List<T>>) -> Unit = {},
    initValue: List<T>? = null,
) {
    private val _state = MutableStateFlow<RemoteStateData<PagingState<T>>>(
        initValue
            ?.let { RemoteState.Success(PagingState(items = it)) }
            ?: RemoteState.Loading,
    )

    /**
     * The collectable pagination state.
     *
     * Sample usage inside a [androidx.lifecycle.ViewModel]:
     * ```kotlin
     * val state = pagination.state
     *     .map { state ->
     *         state.mapSuccess {
     *             val units = it.items.map { repo ->
     *                 RepoUnit.Default(
     *                     repo = repo,
     *                     listener = weakLambda1(this) { repoName ->
     *                         onRepositoryItemSelected(repo)
     *                     }
     *                 )
     *             }
     *             if (it.isNextPageLoading) units + loader else units
     *         }
     *     }
     *     .map { it.mapError(::onGetRepositoriesFailure) }
     *     .stateIn(viewModelScope, SharingStarted.Lazily, RemoteState.Loading)
     * ```
     */
    val state: StateFlow<RemoteStateData<PagingState<T>>> = _state.asStateFlow()

    private var loadFirstPageJob: Job? = null
    private var refreshJob: Job? = null
    private var loadNextPageJob: Job? = null

    /**
     * Loads the first page of data.
     *
     * This operation resets the state to `Loading` and fetches initial data from the data source.
     * If called while other operations are in progress:
     * 1. Cancels any ongoing refresh or next-page loading
     * 2. Waits for completion if a first-page load is already running
     * 3. Ensures only one first-page load executes concurrently
     *
     * The method suspends until the loading operation completes, allowing callers to wait for
     * the operation to finish.
     */
    suspend fun loadFirstPage() {
        loadFirstPageJob?.let { job ->
            job.join()
            return
        }
        cancelRefreshJob()
        cancelLoadNextPageJob()

        coroutineScope {
            loadFirstPageJob = launch {
                _state.value = RemoteState.Loading

                try {
                    val items: List<T> = dataSource.loadPage(list = null)
                    _state.value = RemoteState.Success(
                        data = PagingState(
                            items = items,
                            isEndOfList = dataSource.isPageFull(items).not()
                        ),
                    )
                } catch (e: Exception) {
                    Napier.e("Can't load first page", e)
                    _state.value = RemoteState.Error(e)
                }
            }.apply {
                invokeOnCompletion { loadFirstPageJob = null }
            }
        }
    }

    /**
     * Loads the next page of data.
     *
     * This operation can only execute when:
     * 1. The current state is `Success` (data is already loaded)
     * 2. The end of the list hasn't been reached (`isEndOfList` is false)
     * 3. No other next-page load is currently running
     *
     * The method waits for any ongoing refresh to complete before proceeding to maintain
     * list integrity. It automatically deduplicates items that may have appeared in already
     * loaded pages.
     */
    @Suppress("ReturnCount")
    suspend fun loadNextPage() {
        val currentState: RemoteState.Success<PagingState<T>> =
            _state.value as? RemoteState.Success ?: return

        if (currentState.data.isEndOfList) return

        loadNextPageJob?.let { job ->
            job.join()
            return
        }
        refreshJob?.join()

        coroutineScope {
            loadNextPageJob = launch {
                _state.value = currentState.withNextPageLoading(true)

                runCatching {
                    val currentList: List<T> = currentState.data.items
                    val nextPageItems: List<T> = dataSource.loadPage(list = currentList)
                    val filteredItems: List<T> = nextPageItems.filterNot { item ->
                        currentList.any { isSameItem(it, item) }
                    }
                    val newList: List<T> = currentList + filteredItems

                    _state.value = RemoteState.Success(
                        data = PagingState(
                            items = newList,
                            isEndOfList = dataSource.isPageFull(nextPageItems).not()
                        ),
                    )

                    nextPageItems
                }.onFailure { e ->
                    Napier.e("Can't load next page", e)
                    _state.value = currentState.withNextPageLoading(false)
                }.let { result ->
                    nextPageListener(result)
                }
            }.apply {
                invokeOnCompletion { loadNextPageJob = null }
            }
        }
    }

    /**
     * Refreshes the current list while preserving existing items.
     *
     * This operation:
     * 1. Only executes when in `Success` state
     * 2. Waits for any ongoing next-page load to complete
     * 3. Appends new items to the beginning, or replaces the entire list if no overlap exists
     *
     * The method intelligently merges refreshed items with existing data to maintain
     * a consistent user experience during refresh operations.
     */
    suspend fun refresh() {
        val currentState: RemoteState.Success<PagingState<T>> =
            _state.value as? RemoteState.Success ?: return

        refreshJob?.let {
            it.join()
            return
        }
        loadNextPageJob?.join()

        coroutineScope {
            refreshJob = launch {
                _state.value = currentState.withRefreshing(true)

                runCatching {
                    val currentItems: List<T> = currentState.data.items
                    val newItems: List<T> = dataSource.loadPage(list = null)
                    val filteredItems: List<T> = newItems.filterNot { item ->
                        currentItems.any { isSameItem(item, it) }
                    }

                    val newState = if (filteredItems.size == newItems.size) {
                        PagingState(newItems)
                    } else {
                        PagingState(filteredItems + currentItems)
                    }
                    _state.value = RemoteState.Success(newState)

                    newItems
                }.onFailure { e ->
                    Napier.e("Can't refresh list of services", e)
                    _state.value = currentState.withRefreshing(false)
                }.let { result ->
                    refreshListener(result)
                }
            }.apply {
                invokeOnCompletion { refreshJob = null }
            }
        }
    }

    /**
     * Manually updates the pagination data from external sources.
     *
     * Atomically attempts to update items within an existing `Success` state,
     * or creates a new `Success` state if not currently in one. Cancels all
     * ongoing loading operations to prevent conflicts with external data changes.
     */
    fun setData(items: List<T>?) {
        cancelLoadFirstPageJob()
        cancelRefreshJob()
        cancelLoadNextPageJob()

        _state.update { currentState ->
            when (currentState) {
                is RemoteState.Success -> {
                    val newPagingState = currentState.data.copy(items = items.orEmpty())
                    currentState.copy(data = newPagingState)
                }

                else -> RemoteState.Success(
                    data = PagingState(items = items.orEmpty())
                )
            }
        }
    }

    private fun cancelLoadFirstPageJob() {
        loadFirstPageJob?.let { job ->
            job.cancel()
            loadFirstPageJob = null
        }
    }

    private fun cancelRefreshJob() {
        refreshJob?.let { job ->
            job.cancel()
            refreshJob = null
        }
    }

    private fun cancelLoadNextPageJob() {
        loadNextPageJob?.let { job ->
            job.cancel()
            loadNextPageJob = null
        }
    }
}
