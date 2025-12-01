package dev.kenkoro.lib.feature.sample

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dev.kenkoro.lib.feature.sample.model.SampleRepository
import kotlinx.coroutines.launch

class SampleViewModel @AssistedInject internal constructor(
    @Assisted private val params: Params,
    private val repository: SampleRepository,
) : ViewModel() {
    fun hello() {
        viewModelScope.launch {
            val res = repository.fetch(params.name)
            Log.d("Hello", res)
        }
    }

    data class Params(
        val name: String,
    )
}
