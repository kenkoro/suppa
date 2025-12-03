package dev.kenkoro.app.utils

import dev.kenkoro.app.R

internal sealed interface Destination {
    fun action(): Int

    data object AssistedSampleFragment : Destination {
        override fun action(): Int = R.id.toAssistedSampleFragmentAction
    }
}
