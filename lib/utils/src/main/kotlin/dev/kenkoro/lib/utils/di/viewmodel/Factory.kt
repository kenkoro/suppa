package dev.kenkoro.lib.utils.di.viewmodel

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel

interface Factory<P, VM : ViewModel> {
    fun create(params: P): VM
}

inline fun <reified VM : ViewModel, P, F : Factory<P, VM>> ComponentActivity.getViewModel(
    crossinline injectFactory: () -> F,
    crossinline getParams: () -> P,
): Lazy<VM> {
    return viewModels {
        params { injectFactory().create(getParams()) }
    }
}
