package {{ cookiecutter.pkg_name }}.lib.utils.paging

import {{ cookiecutter.pkg_name }}.lib.utils.state.RemoteState
import {{ cookiecutter.pkg_name }}.lib.utils.state.RemoteStateData
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class PaginationTests {
    /**
     * Helps mimic server responses.
     */
    private lateinit var channel: Channel<List<Int>?>

    private lateinit var pagination: Pagination<Int>

    @BeforeTest
    fun setup() {
        channel = Channel()
        pagination = Pagination(
            dataSource = object : PagingDataSource<Int> {
                override fun isPageFull(list: List<Int>): Boolean = list.isNotEmpty()

                override suspend fun loadPage(list: List<Int>?): List<Int> {
                    return channel
                        .receiveAsFlow()
                        .filterNotNull()
                        .first()
                }
            }
        )
    }

    @Test
    fun `should be loading when initialized`() = runTest {
        val state = pagination.state.value
        assertIs<RemoteState.Loading>(state)
    }

    @Test
    fun `should load the first page when there's an action`() = runTest {
        val expected = PagingState(
            items = listOf(0, 1, 2),
            isRefreshing = false,
            isNextPageLoading = false,
            isEndOfList = false,
        )

        pagination.executePagingAction(
            action = { loadFirstPage() },
            channel = channel,
            response = expected.items,
            onLoading = { assertIs<RemoteState.Loading>(it) },
        )

        pagination.state.value.let { state ->
            assertIs<RemoteState.Success<*>>(state)
            assertEquals(expected, state.data)
        }
    }

    @Test
    fun `should load the next page when there's an action`() = runTest {
        val expectedOnLoading = PagingState(
            items = listOf(0, 1, 2),
            isRefreshing = false,
            isNextPageLoading = true,
            isEndOfList = false,
        )
        val expectedOnLoaded = PagingState(
            items = listOf(0, 1, 2, 3, 4, 5),
            isRefreshing = false,
            isNextPageLoading = false,
            isEndOfList = false,
        )
        pagination.loadInitialPages(channel, response = expectedOnLoading.items) { loadFirstPage() }

        pagination.executePagingAction(
            action = { loadNextPage() },
            channel = channel,
            response = listOf(3, 4, 5),
            onLoading = { state ->
                assertIs<RemoteState.Success<*>>(state)
                assertEquals(expectedOnLoading, state.data)
            },
        )

        pagination.state.value.let { state ->
            assertIs<RemoteState.Success<*>>(state)
            assertEquals(expectedOnLoaded, state.data)
        }
    }

    @Test
    fun `should refresh the page when there's an action`() = runTest {
        val expectedOnLoading = PagingState(
            items = listOf(0, 1, 2, 3, 4, 5),
            isRefreshing = true,
            isNextPageLoading = false,
            isEndOfList = false,
        )
        val expectedOnLoaded = PagingState(
            items = listOf(-1, 0, 1, 2, 3, 4, 5),
            isRefreshing = false,
            isNextPageLoading = false,
            isEndOfList = false,
        )
        pagination.loadInitialPages(channel, response = expectedOnLoading.items) { loadFirstPage() }

        pagination.executePagingAction(
            action = { refresh() },
            channel = channel,
            response = listOf(-1, 0, 1),
            onLoading = { state ->
                assertIs<RemoteState.Success<*>>(state)
                assertEquals(expectedOnLoading, state.data)
            },
        )

        pagination.state.value.let { state ->
            assertIs<RemoteState.Success<*>>(state)
            assertEquals(expectedOnLoaded, state.data)
        }
    }

    @Test
    fun `should load the next page when there are no items left`() = runTest {
        val expectedOnLoading = PagingState(
            items = listOf(-1, 0, 1, 2, 3, 4, 5),
            isRefreshing = false,
            isNextPageLoading = true,
            isEndOfList = false,
        )
        val expectedOnLoaded = PagingState(
            items = listOf(-1, 0, 1, 2, 3, 4, 5),
            isRefreshing = false,
            isNextPageLoading = false,
            isEndOfList = true,
        )
        pagination.loadInitialPages(channel, response = expectedOnLoading.items) { loadFirstPage() }

        pagination.executePagingAction(
            action = { loadNextPage() },
            channel = channel,
            response = emptyList(),
            onLoading = { state ->
                assertIs<RemoteState.Success<*>>(state)
                assertEquals(expectedOnLoading, state.data)
            },
        )

        pagination.state.value.let { state ->
            assertIs<RemoteState.Success<*>>(state)
            assertEquals(expectedOnLoaded, state.data)
        }
    }

    @Test
    fun `should refresh the page when there are new items`() = runTest {
        val expectedOnLoading = PagingState(
            items = emptyList<Int>(),
            isRefreshing = true,
            isNextPageLoading = false,
            isEndOfList = true,
        )
        val expectedOnLoaded = PagingState(
            items = listOf(6, 7),
            isRefreshing = false,
            isNextPageLoading = false,
            isEndOfList = false,
        )
        pagination.loadInitialPages(channel, response = emptyList()) { loadFirstPage() }

        pagination.executePagingAction(
            action = { refresh() },
            channel = channel,
            response = listOf(6, 7),
            onLoading = { state ->
                assertIs<RemoteState.Success<*>>(state)
                assertEquals(expectedOnLoading, state.data)
            },
        )

        pagination.state.value.let { state ->
            assertIs<RemoteState.Success<*>>(state)
            assertEquals(expectedOnLoaded, state.data)
        }
    }

    @Test
    fun `should load the new first page when there's an action`() = runTest {
        val expected = PagingState(
            items = listOf(8, 9),
            isRefreshing = false,
            isNextPageLoading = false,
            isEndOfList = false,
        )
        pagination.loadInitialPages(channel, response = listOf(10)) { loadFirstPage() }

        pagination.executePagingAction(
            action = { loadFirstPage() },
            channel = channel,
            response = listOf(8, 9),
            onLoading = { state ->
                assertIs<RemoteState.Loading>(state)
            },
        )

        pagination.state.value.let { state ->
            assertIs<RemoteState.Success<*>>(state)
            assertEquals(expected, state.data)
        }
    }

    private suspend fun <T> Pagination<T>.loadInitialPages(
        channel: Channel<List<T>?>,
        response: List<T>,
        action: suspend Pagination<T>.() -> Unit,
    ) {
        val pagination = this
        coroutineScope {
            val result = async { pagination.action() }
            channel.send(null)
            channel.send(response)
            result.await()
        }
    }

    private suspend fun <T> Pagination<T>.executePagingAction(
        action: suspend Pagination<T>.() -> Unit,
        channel: Channel<List<T>?>,
        response: List<T>,
        onLoading: (RemoteStateData<PagingState<T>>) -> Unit = {},
    ) {
        val pagination = this
        coroutineScope {
            val result = async { pagination.action() }
            channel.send(null)
            onLoading(pagination.state.value)
            channel.send(response)
            result.await()
        }
    }
}
