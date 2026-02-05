package {{ cookiecutter.pkg_name }}.app.utils

import androidx.annotation.IdRes
import {{ cookiecutter.pkg_name }}.app.R

internal sealed interface Destination {
    @get:IdRes
    val actionId: Int

    data object AssistedSample : Destination {
        override val actionId: Int = R.id.toAssistedSampleFragmentAction
    }
}
