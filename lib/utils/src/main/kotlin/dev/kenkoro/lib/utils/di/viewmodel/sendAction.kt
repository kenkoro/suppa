package dev.kenkoro.lib.utils.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

fun <T> ViewModel.sendAction(
    actions: Channel<T>,
    action: T,
) {
    viewModelScope.launch {
        actions.send(action)
    }
}
