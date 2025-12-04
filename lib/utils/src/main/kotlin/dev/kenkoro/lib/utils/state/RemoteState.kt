package dev.kenkoro.lib.utils.state

import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Represents the lifecycle state of a remote data operation.
 *
 * This implementation uses a class with non-nullable type parameters
 * to ensure cross-platform compatibility with iOS, enabling hashable
 * conformance and avoiding nullable type propagation.
 */
sealed class RemoteState<out T : Any, out E : Any> {
    data object Loading : RemoteState<Nothing, Nothing>()
    data class Success<T : Any>(val data: T) : RemoteState<T, Nothing>()
    data class Error<E : Any>(
        val error: E,
    ) : RemoteState<Nothing, E>()
}

typealias RemoteStateData<T> = RemoteState<T, Throwable>
typealias RemoteStateUi<T> = RemoteState<T, StringDesc>

fun <K : Any, T : Any, E : Any> RemoteState<T, E>.mapSuccess(map: (T) -> K): RemoteState<K, E> {
    return when (this) {
        is RemoteState.Success -> RemoteState.Success(map(this.data))
        is RemoteState.Error -> this
        RemoteState.Loading -> RemoteState.Loading
    }
}

fun <K : Any, T : Any, E : Any> RemoteState<T, E>.mapError(map: (E) -> K): RemoteState<T, K> {
    return when (this) {
        is RemoteState.Success -> this
        is RemoteState.Error -> RemoteState.Error(map(this.error))
        RemoteState.Loading -> RemoteState.Loading
    }
}

fun <T : Any, E : Any> RemoteState<T, E>.isLoading(): Boolean = this is RemoteState.Loading

fun <T : Any, E : Any> RemoteState<T, E>.isSuccess(): Boolean = this is RemoteState.Success

fun <T : Any, E : Any> RemoteState<T, E>.isError(): Boolean = this is RemoteState.Error

fun <T : List<*>, E : Any> RemoteState<T, E>.isEmpty(): Boolean = this.data?.isEmpty() ?: false

fun <T : List<*>, E : Any> RemoteState<T, E>.isErrorOrEmpty(): Boolean =
    this.isError() || this.isEmpty()

fun <T : List<*>, E : Any> RemoteState<T, E>.isNonEmptySuccess(): Boolean =
    this.isSuccess() && this.isEmpty().not()

val <T : Any, E : Any> RemoteState<T, E>.data: T? get() = (this as? RemoteState.Success<T>)?.data

/**
 * Attempts to atomically modify data within a `RemoteState.Success` state.
 *
 * Uses optimistic concurrency control via `compareAndSet` to safely update
 * the state only if it remains `Success` between read and write operations.
 * Returns immediately if the current state is not `Success`.
 *
 * Here, the [function] represents a transformation to apply to the current `Success` data.
 * This function returns `true` if update succeeded, `false` if state is no longer `Success`.
 */
fun <T : Any, E : Any> MutableStateFlow<RemoteState<T, E>>.tryUpdateSuccess(
    function: (T) -> T
): Boolean {
    while (true) {
        val currentState: RemoteState.Success<T> =
            this.value as? RemoteState.Success<T> ?: return false

        val newState: RemoteState.Success<T> = currentState.copy(
            data = function(currentState.data)
        )
        if (this.compareAndSet(expect = currentState, update = newState)) return true
    }
}
