package ui

import domain.Device

data class MainUiState(
    val devices: List<Device> = emptyList(),
    val isLoading: Boolean = false,
    val errorDevices: Boolean = false,
    val errorpkill: Boolean = false
)