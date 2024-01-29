package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(mainController: MainController) {
    val uiState by mainController.uiState.collectAsState()
    MainContent(uiState) {
        mainController.onClickDevices()
    }
}

@Composable
private fun MainContent(uiState: MainUiState, onClickDevices: () -> Unit) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onClickDevices) {
            Text("Show adb devices")
        }
        Text("Compose: ${uiState.devices}")
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainContent(
            uiState = MainUiState(
                "Pixel 7 pro"
            )
        ) {}
    }
}