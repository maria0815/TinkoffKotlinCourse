package orders

import java.time.LocalDate

class OrderService {
    fun findAll(): List<Order> = listOf(
        Order(1, LocalDate.of(2021, 1, 15), 2),
        Order(2, LocalDate.of(2021, 2, 25), 1),
    )
}