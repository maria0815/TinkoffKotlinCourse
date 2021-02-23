interface Car {
    val manufacturer: String
    val model: String
    val color: String
    var currentSpeed: Int

    fun carInfo() = "Информация о машине: ".prettyPrint()

    fun String.prettyPrint() {
        println("Производитель: '${manufacturer}' Модель: '${model}' Цвет: '${color}' Текущая скорость: '${currentSpeed}':")
        println(this)
        println()
    }

    fun increaseSpeed() {
        currentSpeed += 30
    }

    fun slowDown() {
        currentSpeed -= 30
    }

    fun currentSpeedInfo() {
        "Текущая скорость= $currentSpeed".prettyPrint()
    }

    fun turnOnTheWipers()
}