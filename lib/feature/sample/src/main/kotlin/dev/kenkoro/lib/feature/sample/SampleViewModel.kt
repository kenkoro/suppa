package dev.kenkoro.lib.feature.sample

import androidx.lifecycle.ViewModel
import dev.kenkoro.lib.feature.sample.model.SampleRepository
import io.github.aakira.napier.Napier
import javax.inject.Inject

class SampleViewModel @Inject internal constructor(
    private val repository: SampleRepository,
) : ViewModel() {
    fun hello() {
        Napier.d { "Hello :)" }
    }
}