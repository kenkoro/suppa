package dev.kenkoro.app.utils

import androidx.navigation.fragment.NavHostFragment
import dev.kenkoro.app.R
import dev.kenkoro.app.di.DaggerActivity

internal fun DaggerActivity.requireNavHostFragment(): NavHostFragment {
    val navHostFragment =
        supportFragmentManager.findFragmentById(R.id.navHostFragment) as? NavHostFragment
    requireNotNull(navHostFragment) { "The nav host fragment was not found" }
    return navHostFragment
}
