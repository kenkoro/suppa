package dev.kenkoro.utils.flow.binding

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun <T> StateFlow<T>.bind(
    lifecycleOwner: LifecycleOwner,
    observer: (T) -> Unit,
): DisposableHandle {
    val self: StateFlow<T> = this
    val job: Job = lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            self.onEach { observer(it) }.collect()
        }
    }
    return DisposableHandle { job.cancel() }
}

fun <T> Flow<T>.bind(
    lifecycleOwner: LifecycleOwner,
    observer: (T) -> Unit,
): DisposableHandle {
    val self: Flow<T> = this
    val job: Job = lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            self.onEach { observer(it) }.collect()
        }
    }
    return DisposableHandle { job.cancel() }
}
