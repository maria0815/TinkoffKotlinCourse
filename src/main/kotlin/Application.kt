fun main() {
    val hyundaiMighty = Truck("Hyundai", "Hyundai Mighty", "black", 0, "Еврофура")
    val fordMondeo = Automobile("Ford", "Ford Mondeo", "grey", 0, "hatchback ")
    val volvoB9L = Bus("Volvo", "Volvo B9L CNG 7700", "green", 0, 2)
    val cabriolet = Convertible("Mercedes", "Mercedes-Benz E-Class Cabriolet", "red", 0, "Универсал", "Тент")

    val cars = listOf(fordMondeo, cabriolet)

    cars.forEach {
        it.openBoot()
        it.closeBoot()

    }

    with(hyundaiMighty) {
        lowerTheBody()
        liftTheBody()
        increaseSpeed()
    }

    with(volvoB9L) {
        openDoors()
        closeDoors()
        currentSpeedInfo()
        turnOnTheWipers()
        increaseSpeed()
        increaseSpeed()
        currentSpeedInfo()
    }

    fordMondeo.openBoot()
    fordMondeo.closeBoot()
    fordMondeo.currentSpeedInfo()
    fordMondeo.turnOnTheWipers()
    fordMondeo.increaseSpeed()
    fordMondeo.currentSpeedInfo()
    fordMondeo.slowDown()

    cabriolet.openBoot()
    cabriolet.closeBoot()
    cabriolet.increaseSpeed()
    cabriolet.turnOnTheWipers()
    cabriolet.openRoof()
    cabriolet.lowerRoof()
    cabriolet.currentSpeedInfo()
}