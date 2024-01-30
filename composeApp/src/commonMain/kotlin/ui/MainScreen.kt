package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import domain.Device

@Composable
fun MainScreen(mainController: MainController) {
    val uiState by mainController.uiState.collectAsState()
    MainContent(uiState, {
        mainController.onClickDevices()
    }) {
        mainController.runpkill()
    }
}

@Composable
private fun MainContent(uiState: MainUiState, onClickDevices: () -> Unit, onClickpkill: () -> Unit) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = onClickDevices) {
            Text("Show adb devices")
        }
        Button(onClick = onClickpkill) {
            Text("Kill adb processes")
        }
        when {
            uiState.errorDevices -> {
                Text("Error getting devices", color = Color.Red)
            }

            uiState.errorpkill -> {
                Text("Error pkillping", color = Color.Red)
            }

            uiState.devices.isEmpty() -> {
                Text("No device connected")
            }

            else -> {
                LazyColumn {
                    items(uiState.devices) {
                        Text(it.name)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainContent(
            uiState = MainUiState(
                devices = listOf(
                    Device(
                        name = "Pixel 7 pro"
                    )
                )
            ),
            {}, {}
        )
    }
}

@Preview
@Composable
fun MainScreenErrorDevicesPreview() {
    MaterialTheme {
        MainContent(
            uiState = MainUiState(
                devices = listOf(
                    Device(
                        name = "Pixel 7 pro",
                    )
                ),
                errorDevices = true
            ),
            {}, {}
        )
    }
}

@Preview
@Composable
fun MainScreenErrorpkillPreview() {
    MaterialTheme {
        MainContent(
            uiState = MainUiState(
                devices = listOf(
                    Device(
                        name = "Pixel 7 pro"
                    )
                ),
                errorpkill = true
            ),
            {}, {}
        )
    }
}