package ui

import common.CoroutineController
import domain.commands.CommandDevices
import domain.CommandProcessor
import domain.Device
import domain.commands.Commandpkill
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainController : CoroutineController() {
    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val commandProcessor = CommandProcessor()

    fun onClickDevices() {
        mainControllerScope.launch {
            updateStateLoading(true)
            commandProcessor.process(CommandDevices())
                .fold(
                    { devices ->
                        _uiState.update { mainState ->
                            mainState.copy(devices = devices
                                .split('\n')
                                .drop(1)
                                .filter { it.isNotEmpty() }
                                .map { deviceName -> Device(deviceName) }
                            )
                        }
                        updateStateLoading(false)
                    }, {
                        updateStateLoading(false)
                        _uiState.update { mainState ->
                            mainState.copy(
                                errorDevices = true
                            )
                        }
                    })
        }
    }

    fun runpkill() {
        updateStateLoading(true)
        mainControllerScope.launch {
            val resultDeferred = async { commandProcessor.process(Commandpkill()) }
            resultDeferred.await().fold(
                {
                    updateStateLoading(false)
                },
                {
                    updateStateLoading(false)
                    _uiState.update { mainState ->
                        mainState.copy(
                            errorpkill = true
                        )
                    }
                }
            )
        }
    }

    private fun updateStateLoading(loading: Boolean) {
        _uiState.update { mainState ->
            mainState.copy(
                isLoading = loading
            )
        }
    }
}