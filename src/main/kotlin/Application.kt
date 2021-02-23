fun main() {
    val hyundaiMighty = Truck("Hyundai",
            "Hyundai Mighty",
            "black",
            0,
            "Еврофура")

    val fordMondeo = Automobile("Ford",
            "Ford Mondeo",
            "grey",
            0,
            "hatchback ")

    val volvoB9L = Bus("Volvo",
            "Volvo B9L CNG 7700",
            "green",
            0,
            2)

    val cabriolet = Convertible("Mercedes",
            "Mercedes-Benz E-Class Cabriolet",
            "red",
            0,
            "Универсал",
            "Тент")

    val cars = listOf(hyundaiMighty, fordMondeo, volvoB9L, cabriolet)

    cars.forEach {
        it.currentSpeedInfo()
    }

    with(hyundaiMighty) {
        liftTheBody()
        lowerTheBody()
        increaseSpeed(30)
        increaseSpeed()
        currentSpeedInfo()
        increaseSpeed(20)
        slowDown()
        slowDown(10)
        currentSpeedInfo()
    }

    with(volvoB9L) {
        openDoors()
        closeDoors()
        currentSpeedInfo()
        increaseSpeed()
        turnOnTheWipers()
        increaseSpeed()
        currentSpeedInfo()
    }

    with(fordMondeo) {
        openBoot()
        closeBoot()
        currentSpeedInfo()
        turnOnTheWipers()
        increaseSpeed()
        currentSpeedInfo()
        slowDown()
    }

    with(cabriolet) {
        openBoot()
        closeBoot()
        increaseSpeed()
        turnOnTheWipers()
        openRoof()
        lowerRoof()
        currentSpeedInfo()
    }
}