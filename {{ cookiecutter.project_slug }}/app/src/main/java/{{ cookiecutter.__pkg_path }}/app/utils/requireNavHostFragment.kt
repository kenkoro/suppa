package {{ cookiecutter.pkg_name }}.app.utils

import androidx.navigation.fragment.NavHostFragment
import {{ cookiecutter.pkg_name }}.app.R
import {{ cookiecutter.pkg_name }}.app.di.DaggerActivity

internal fun DaggerActivity.requireNavHostFragment(): NavHostFragment {
    val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.navHostFragment) as? NavHostFragment
    requireNotNull(navHostFragment) { "The nav host fragment was not found" }
    return navHostFragment
}
