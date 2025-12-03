package dev.kenkoro.app.di

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

internal abstract class NoParamsFragment<VB : ViewBinding> : DaggerFragment<VB>() {
    @Inject
    lateinit var defaultFactory: ViewModelProvider.Factory

    inline fun <reified VM : ViewModel> getViewModel(
        noinline extrasProducer: (() -> CreationExtras)? = null,
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null,
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: { defaultFactory }

        return viewModels(extrasProducer = extrasProducer, factoryProducer = factoryPromise)
    }
}
