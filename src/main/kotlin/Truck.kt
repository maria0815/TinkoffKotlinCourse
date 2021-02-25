class Truck(manufacturer: String,
            model: String,
            color: String,
            currentSpeed: Int,
            private val body: String) : Car(manufacturer, model, color, currentSpeed) {

    override fun increaseSpeed() {
        currentSpeed += 15
        "Скорость увеличена на 15".prettyPrint()
    }

    fun increaseSpeed(number: Int) {
        currentSpeed += number
        "Скорость увеличена на '$number'".prettyPrint()
    }

    override fun slowDown() {
        currentSpeed -= 15
        "Скорость уменьшена на 15".prettyPrint()
    }

    override fun currentSpeedInfo(): Int {
        "Текущая скорость равна '$currentSpeed'".prettyPrint()
        return currentSpeed
    }

    fun slowDown(number: Int) {
        currentSpeed -= number
        "Скорость уменьшена на '$number'".prettyPrint()
    }

    override fun turnOnTheWipers() {
        "Окно очищено!".prettyPrint()
    }

    fun lowerTheBody() {
        "Кузов типа $body опущен".prettyPrint()
    }

    fun liftTheBody() {
        "Кузов типа $body поднят".prettyPrint()
    }
}