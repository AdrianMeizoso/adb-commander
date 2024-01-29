package ui

import common.CoroutineController
import domain.CommandDevices
import domain.CommandProcessor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainController : CoroutineController() {
    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState("Loading"))
    val uiState = _uiState.asStateFlow()
    
    private val commandProcessor = CommandProcessor()
    
    fun onClickDevices() {
        mainControllerScope.launch {
            val result = commandProcessor.process(CommandDevices())
            _uiState.update {
                it.copy(
                    devices = result
                )
            }
        }
    }
}