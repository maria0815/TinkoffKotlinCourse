package entity

/**
 * Хранит информацию о заказе и клиенте.
 */
data class OrderWithClient(
    val orderId: Int,
    val clientId: Int,
    val name: String,
    val address: String,
    val price: Double
) {
    constructor(order: Order, client: Client) : this(
        order.id,
        client.id,
        client.name,
        client.address,
        order.price
    )
}