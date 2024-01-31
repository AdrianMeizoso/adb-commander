package domain.commands

import domain.Command

class CommandDevices: Command {
    override val timeout = 3000L

    override fun instrucction() : String {
        return "adb devices -l"
    }

    override fun map(output: String): String {
        return output
    }
}