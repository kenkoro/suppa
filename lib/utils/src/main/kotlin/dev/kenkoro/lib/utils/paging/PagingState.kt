package dev.kenkoro.lib.utils.paging

/**
 * Immutable state container for paginated list presentations.
 */
data class PagingState<T>(
    val items: List<T>,
    val isRefreshing: Boolean = false,
    val isNextPageLoading: Boolean = false,
    val isEndOfList: Boolean = false
)
