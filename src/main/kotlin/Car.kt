abstract class Car(val manufacturer: String,
                   val model: String,
                   val color: String,
                   var currentSpeed: Int) {

    fun String.prettyPrint() {
        print("""
           |Производитель: '$manufacturer'
           |Модель: '$model'
           |Цвет: '$color'
           |Текущая скорость: '$currentSpeed'
           |""".trimMargin())
        println(this)
        println()
    }

    abstract fun increaseSpeed()

    abstract fun slowDown()

    abstract fun currentSpeedInfo(): Int

    abstract fun turnOnTheWipers()
}