package dev.kenkoro.app.di

import androidx.lifecycle.ViewModel
import dev.kenkoro.lib.utils.di.viewmodel.Factory
import dev.kenkoro.lib.utils.di.viewmodel.getViewModel
import javax.inject.Inject

internal open class ParamsActivity<F : Factory<*, *>> : DaggerActivity() {
    @Inject
    lateinit var assistedFactory: F

    inline fun <P, reified VM : ViewModel> getViewModel(crossinline getParams: () -> P): Lazy<VM> {
        return getViewModel(
            injectFactory = {
                @Suppress("UNCHECKED_CAST")
                assistedFactory as Factory<P, VM>
            },
            getParams = getParams,
        )
    }
}
