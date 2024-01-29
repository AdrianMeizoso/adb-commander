package common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.jetbrains.skiko.MainUIDispatcher

abstract class CoroutineController {
    val mainControllerScope = CoroutineScope(SupervisorJob() + MainUIDispatcher)
}