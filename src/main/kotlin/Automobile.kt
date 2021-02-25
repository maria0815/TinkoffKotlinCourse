open class Automobile(manufacturer: String,
                      model: String,
                      color: String,
                      currentSpeed: Int,
                      val boot: String) : Car(manufacturer, model, color, currentSpeed) {

    open fun openBoot() {
        "Багажник типа $boot открыт".prettyPrint()
    }

    open fun closeBoot() {
        "Багажник типа $boot закрыт".prettyPrint()
    }

    override fun increaseSpeed() {
        currentSpeed += 20
        "Скорость увеличена на 20".prettyPrint()
    }

    override fun slowDown() {
        currentSpeed -= 20
        "Скорость уменьшена на 20".prettyPrint()
    }

    override fun currentSpeedInfo(): Int {
        "Текущая скорость равна '$currentSpeed'".prettyPrint()
        return currentSpeed
    }

    override fun turnOnTheWipers() {
        "Окно очищено!".prettyPrint()
    }
}