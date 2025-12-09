package {{ cookiecutter.pkg_name }}.lib.feature.sample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.errors.handler.ExceptionHandler
import {{ cookiecutter.pkg_name }}.lib.feature.sample.model.SampleRepository
import {{ cookiecutter.pkg_name }}.lib.utils.di.viewmodel.sendAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SampleViewModel @Inject internal constructor(
    private val repository: SampleRepository,
    val exceptionHandler: ExceptionHandler,
) : ViewModel() {
    private val _actions = Channel<Actions>()
    val actions: Flow<Actions> = _actions.receiveAsFlow()

    fun onStart() {
        viewModelScope.launch {
            repository.get()
        }
    }

    fun onCentralTitleClicked() {
        sendAction(_actions, Actions.RouteToAssistedSample)
    }

    sealed interface Actions {
        data object RouteToAssistedSample : Actions
    }
}
