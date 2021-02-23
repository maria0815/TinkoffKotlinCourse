class Bus(override val manufacturer: String, override val model: String, override val color: String, override var currentSpeed: Int, private val numberOfDoors: Int) : Car {

    fun openDoors() {
        "Двери открыты".prettyPrint()
    }

    fun closeDoors() {
        "Двери закрыты".prettyPrint()
    }

    override fun turnOnTheWipers() {
    }
}