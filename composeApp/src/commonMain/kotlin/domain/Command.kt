package domain

interface Command {
    val timeout: Long
    fun instrucction(): String
    fun map(output: String): String
}