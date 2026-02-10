package dev.kenkoro.app.di

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.viewbinding.ViewBinding
import dev.kenkoro.app.components.BoundFragment
import javax.inject.Inject

abstract class NoParamsFragment<VB : ViewBinding> : BoundFragment<VB>() {
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
