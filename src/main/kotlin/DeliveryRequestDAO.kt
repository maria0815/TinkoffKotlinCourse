interface DeliveryRequestDAO {
    /**
     * Возвращает заявку на доставку по [id]
     */
    fun getDeliveryRequestById(id: Int): DeliveryRequest?

    /**
     * Возращает список всех заявок на доставку
     */
    fun getAllDeliveryRequests(): List<DeliveryRequest>
}

/**
 * Заявка на доставку
 */
data class DeliveryRequest(
    var id: Int = 0,
    var address: String = "",
)