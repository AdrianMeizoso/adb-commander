package domain

interface Command {
    fun instrucction(): String
    fun map(output: String): String
}