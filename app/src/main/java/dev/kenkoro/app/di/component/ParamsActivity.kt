package dev.kenkoro.app.di.component

import androidx.lifecycle.ViewModel
import dev.kenkoro.feature.utils.di.Factory
import dev.kenkoro.feature.utils.di.getViewModel
import javax.inject.Inject

open class ParamsActivity<F : Factory<*, *>> : DaggerActivity() {
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
