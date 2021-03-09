package dao

import entity.Order
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class OrderDAOTest {

    @Test
    fun `method findAll returns a list of all orders`() {
        val listOfOrders = listOf(
            Order(1, 2, 500.00),
            Order(2, 1, 200.00),
            Order(3, 2, 200.00)
        )
        val result = OrderDAO().findAll()
        assertEquals(listOfOrders, result)
    }

    @Test
    fun `method findByClientId returns orders according to the specified customer id`() {
        val listOfOrders = listOf(
            Order(1, 2, 500.00),
            Order(3, 2, 200.00)
        )
        val result = OrderDAO().findByClientId(2)
        assertEquals(listOfOrders, result)
    }

    @Test
    fun `returns an empty list of orders for non-existent client`() {
        val listOfOrders = emptyList<Order>()
        val result = OrderDAO().findByClientId(4)
        assertEquals(listOfOrders, result)
    }
}