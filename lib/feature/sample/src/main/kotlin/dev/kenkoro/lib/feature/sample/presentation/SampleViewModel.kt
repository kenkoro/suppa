package dev.kenkoro.lib.feature.sample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kenkoro.lib.feature.sample.model.SampleRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import javax.inject.Inject

class SampleViewModel @Inject constructor(
    private val repository: SampleRepository,
) : ViewModel() {
    fun onStart() {
        viewModelScope.launch {
            repository.get()
            Napier.d { "Everything's working" }
        }
    }
}
