package {{ cookiecutter.pkg_name }}.feature.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.errors.handler.ExceptionHandler
import {{ cookiecutter.pkg_name }}.domain.sample.interactor.GetSampleData
import {{ cookiecutter.pkg_name }}.feature.utils.sendAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SampleViewModel @Inject constructor(
    private val getData: GetSampleData,
    val exceptionHandler: ExceptionHandler,
) : ViewModel() {
    private val _actions = Channel<Actions>()
    val actions: Flow<Actions> = _actions.receiveAsFlow()

    fun onStart() {
        viewModelScope.launch { getData.await() }
    }

    fun onCentralTitleClicked() {
        sendAction(_actions, Actions.RouteToAssistedSample)
    }

    sealed interface Actions {
        data object RouteToAssistedSample : Actions
    }
}
