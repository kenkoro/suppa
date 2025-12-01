package dev.kenkoro.lib.feature.sample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.kenkoro.lib.feature.sample.model.SampleRepository
import kotlinx.coroutines.launch

class AssistedSampleViewModel @AssistedInject internal constructor(
    @Assisted private val params: Params,
    private val repository: SampleRepository,
) : ViewModel() {
    fun onStart() {
        viewModelScope.launch {
            repository.get(params.name)
        }
    }

    data class Params(
        val name: String,
    )
}
