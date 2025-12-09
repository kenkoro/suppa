package {{ cookiecutter.pkg_name }}.lib.utils.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Creates a [ViewModelProvider.Factory] for a specific, single [ViewModel] type that requires
 * runtime parameters or uses Dagger's `@AssistedInject` mechanism.
 *
 * This factory is designed to be used inline with the `activity-ktx` or `fragment-ktx`
 * `viewModels { ... }` extension function, allowing access to injected factories and local parameters.
 *
 * Sample:
 * ```kotlin
 * class SampleActivity : DaggerActivity() {
 *     @Inject
 *     lateinit var assistedFactory: SampleViewModelFactory
 *
 *     private val viewModel: SampleViewModel by viewModels {
 *         params { assistedFactory.create(SampleViewModel.Params(name = "sample")) }
 *     }
 * }
 * ```
 */
inline fun <reified VM : ViewModel> params(
    crossinline get: () -> VM,
): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass.isAssignableFrom(VM::class.java)) {
                "Unknown view-model class $modelClass"
            }
            @Suppress("UNCHECKED_CAST")
            return get() as T
        }
    }
}
