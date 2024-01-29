import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import ui.MainController
import ui.MainScreen

@Composable
fun App() {
    val mainController = MainController()
    MaterialTheme {
        MainScreen(mainController)
    }
}