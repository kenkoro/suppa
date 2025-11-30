package dev.kenkoro.lib.feature.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kenkoro.lib.feature.sample.model.SampleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SampleViewModel @Inject internal constructor(
    private val repository: SampleRepository,
) : ViewModel() {
    fun hello() {
        viewModelScope.launch {
            repository.fetch()
        }
    }
}
