open class Automobile(override val manufacturer: String, override val model: String, override val color: String, override var currentSpeed: Int, val boot: String) : Car {
    open fun openBoot() {
        "Багажник открыт".prettyPrint()
    }

    open fun closeBoot() {
        "Багажник закрыт".prettyPrint()
    }

    override fun turnOnTheWipers() {
    }
}