package {{ cookiecutter.pkg_name }}.app.utils

import {{ cookiecutter.pkg_name }}.app.R

internal sealed interface Destination {
    fun action(): Int

    data object AssistedSampleFragment : Destination {
        override fun action(): Int = R.id.toAssistedSampleFragmentAction
    }
}
