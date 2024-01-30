package domain.commands

import domain.Command

class Commandpkill: Command {
    override val timeout = 5000L

    override fun instrucction() : String {
        return "pkill adb"
    }

    override fun map(output: String): String {
        return output
    }
}