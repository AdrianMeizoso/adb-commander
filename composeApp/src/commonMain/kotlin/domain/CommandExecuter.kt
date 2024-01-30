package domain

import kotlinx.coroutines.*
import java.io.BufferedReader
import java.lang.Exception
import java.nio.charset.Charset
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import kotlin.coroutines.coroutineContext

class CommandProcessor {
    suspend fun process(command: Command): Result<String> {
        return runCatching {
            withContext(Dispatchers.IO) {
                val process = Runtime.getRuntime().exec(command.instrucction())
                process.waitFor(command.timeout, TimeUnit.MILLISECONDS)
                val reader = process.inputStream.bufferedReader(Charset.defaultCharset())
                command.map(reader.readText())
                    .also { reader.close() }
            }
        }
    }

    companion object {
        const val GLOBAL_TIMEOUT = 5000L
    }
}
