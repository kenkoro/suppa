package {{ cookiecutter.pkg_name }}.feature.assistedSample

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class AssistedSampleViewModel @AssistedInject internal constructor(
    @Suppress("UnusedPrivateProperty")
    @Assisted private val params: Params,
) : ViewModel() {
    fun onStart() {
        // TODO:
    }

    data class Params(
        val name: String,
    )
}
