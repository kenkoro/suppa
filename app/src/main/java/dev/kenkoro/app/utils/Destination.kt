package dev.kenkoro.app.utils

import androidx.annotation.IdRes
import dev.kenkoro.app.R

internal sealed interface Destination {
    @get:IdRes
    val actionId: Int

    data object AssistedSample : Destination {
        override val actionId: Int = R.id.toAssistedSampleFragmentAction
    }
}
