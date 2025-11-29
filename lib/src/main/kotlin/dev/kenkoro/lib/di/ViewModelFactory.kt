package dev.kenkoro.lib.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/** Map-based view-model factory */
internal class ViewModelFactory @Inject constructor(
    private val viewModels: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var provider: Provider<out ViewModel>? = viewModels[modelClass]
        if (provider == null) {
            for ((key, value) in viewModels) {
                if (modelClass.isAssignableFrom(key)) {
                    provider = value
                    break
                }
            }
        }
        if (provider == null) throw IllegalArgumentException("Unknown view-model class $modelClass")
        try {
            @Suppress("UNCHECKED_CAST")
            return provider.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e.cause)
        }
    }
}