class Truck(override val manufacturer: String, override val model: String, override val color: String, override var currentSpeed: Int, private val body: String) : Car {

    override fun increaseSpeed(){
        currentSpeed+=20
    }

    override fun slowDown(){
        currentSpeed-=20
    }

    override fun turnOnTheWipers() {
    }

    fun lowerTheBody(){
        "Кузов опущен".prettyPrint()
    }

    fun liftTheBody (){
        "Кузов поднят".prettyPrint()
    }
}