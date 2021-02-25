class Bus(manufacturer: String,
          model: String,
          color: String,
          currentSpeed: Int,
          private val numberOfDoors: Int) : Car(manufacturer, model, color, currentSpeed) {

    fun openDoors() {
        "Двери открыты".prettyPrint()
    }

    fun closeDoors() {
        "Двери закрыты".prettyPrint()
    }

    fun infoOfNumberDoors() {
        "Количество дверей в автобусе: '$numberOfDoors'".prettyPrint()
    }

    override fun increaseSpeed() {
        currentSpeed += 10
        "Скорость увеличена на 10".prettyPrint()
    }

    override fun slowDown() {
        currentSpeed -= 10
        "Скорость уменьшена на 10".prettyPrint()
    }

    override fun currentSpeedInfo(): Int {
        "Текущая скорость равна '$currentSpeed'".prettyPrint()
        return currentSpeed
    }

    override fun turnOnTheWipers() {
        "Окно очищено!".prettyPrint()
    }
}