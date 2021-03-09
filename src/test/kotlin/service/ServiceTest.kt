package service

import entity.OrderWithClient
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ServiceTest {

    @Test
    fun getAll() {
        val listOfOrderWithClient = listOf(
            OrderWithClient(1, 2, "Ростелеком", "МСК", 500.00),
            OrderWithClient(2, 1, "Роснефть", "СПб", 200.00),
            OrderWithClient(3, 2, "Ростелеком", "МСК", 200.00)
        )
        val result = OrderWithClientService().getAll()
        assertEquals(listOfOrderWithClient, result)
    }

    @Test
    fun getNumberOfFilteredOrders() {
        assertEquals(1, OrderWithClientService().getNumberOfFilteredOrders { it.price > 200 })
        assertEquals(3, OrderWithClientService().getNumberOfFilteredOrders { it.price > 0 })
        assertEquals(0, OrderWithClientService().getNumberOfFilteredOrders { it.price > 500 })
    }

    @Test
    fun getSortedByClientId() {
        val listOfOrderWithClient = listOf(
            OrderWithClient(2, 1, "Роснефть", "СПб", 200.00),
            OrderWithClient(1, 2, "Ростелеком", "МСК", 500.00),
            OrderWithClient(3, 2, "Ростелеком", "МСК", 200.00)
        )
        val result = OrderWithClientService().getSortedByClientId()
        assertEquals(listOfOrderWithClient, result)
    }

    @Test
    fun getGroupedByPrice() {
        val map = mapOf(
            200.00 to listOf(
                OrderWithClient(2, 1, "Роснефть", "СПб", 200.00),
                OrderWithClient(3, 2, "Ростелеком", "МСК", 200.00)
            ),
            500.00 to listOf(OrderWithClient(1, 2, "Ростелеком", "МСК", 500.00))
        )
        val result = OrderWithClientService().getGroupedByPrice()
        assertEquals(map, result)
    }
}