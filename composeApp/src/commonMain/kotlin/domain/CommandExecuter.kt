package domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader

class CommandProcessor {
    suspend fun process(command: Command): String {
        return withContext(Dispatchers.IO) {
            val process = Runtime.getRuntime().exec(command.instrucction())
            process.waitFor()
            val inputStream = process.inputStream
            val reader = BufferedReader(inputStream.reader())
            command.map(reader.readText())
        }
    }
}