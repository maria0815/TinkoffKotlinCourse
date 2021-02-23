class Convertible(override val manufacturer: String, override val model: String, override val color: String, override var currentSpeed: Int, boot: String, private val roof:String) : Automobile(manufacturer, model, color, currentSpeed, boot) {

    override fun openBoot() {
        "Багажник кабриолета открыт".prettyPrint()
    }

    override fun closeBoot(){
        "Багажник кабриолета закрыт".prettyPrint()
    }

    fun openRoof(){
        "Крыша поднята".prettyPrint()
    }

    fun lowerRoof(){
        "Крыша опущена".prettyPrint()
    }
}