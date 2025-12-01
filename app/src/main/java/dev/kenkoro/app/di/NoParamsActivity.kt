package dev.kenkoro.app.di

import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import javax.inject.Inject

internal open class NoParamsActivity : DaggerActivity() {
    @Inject
    lateinit var defaultFactory: ViewModelProvider.Factory

    inline fun <reified VM : ViewModel> getViewModel(
        noinline extrasProducer: (() -> CreationExtras)? = null,
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null,
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: { defaultFactory }

        return viewModels(extrasProducer, factoryPromise)
    }
}
