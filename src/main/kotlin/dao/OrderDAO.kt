package dao

import entity.Order

class OrderDAO {
    private val listOfOrders = listOf(
        Order(1, 2, 500.00),
        Order(2, 1, 200.00),
        Order(3, 2, 200.00)
    )

    /**
     * Возвращает список всех заказов.
     */
    fun findAll(): List<Order> = listOfOrders

    /**
     * Возвращает список заказов для клиентов,
     * у которых идентификатор равен [clientId].
     */
    fun findByClientId(clientId: Int): List<Order> = listOfOrders.filter { it.clientId == clientId }
}