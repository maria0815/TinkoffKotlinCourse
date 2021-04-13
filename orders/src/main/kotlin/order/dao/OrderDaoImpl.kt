package order.dao

import order.model.Order
import java.time.LocalDate

class OrderDaoImpl : OrderDao {
    val orders = listOf(
        Order(1, LocalDate.of(2021, 10, 1), 1),
        Order(2, LocalDate.of(2021, 5, 7), 2),
        Order(3, LocalDate.of(2021, 12, 15), 3),
        Order(4, LocalDate.of(2021, 6, 9), 3),
        Order(5, LocalDate.of(2021, 1, 20), 4)
    )

    override fun getById(id: Int): Order? = orders.firstOrNull { it.id == id }

    override fun getAll(): List<Order> = orders
}