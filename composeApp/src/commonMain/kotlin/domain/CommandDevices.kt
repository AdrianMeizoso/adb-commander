package domain

class CommandDevices: Command {
    override fun instrucction() : String {
        return "adb devices"
    }

    override fun map(output: String): String {
        return output
    }
}