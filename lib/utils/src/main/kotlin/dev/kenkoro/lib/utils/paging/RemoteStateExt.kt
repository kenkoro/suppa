package dev.kenkoro.lib.utils.paging

import dev.kenkoro.lib.utils.state.RemoteState

/**
 * Updates the `isRefreshing` property inside a [PagingState] and preserves all other state data.
 */
fun <T> RemoteState.Success<PagingState<T>>.withRefreshing(
    value: Boolean
): RemoteState.Success<PagingState<T>> = this.copy(data = this.data.copy(isRefreshing = value))

/**
 * Updates the `isNextPageLoading` property inside a [PagingState] and preserves all other state data.
 */
fun <T> RemoteState.Success<PagingState<T>>.withNextPageLoading(
    value: Boolean
): RemoteState.Success<PagingState<T>> = this.copy(data = this.data.copy(isNextPageLoading = value))
