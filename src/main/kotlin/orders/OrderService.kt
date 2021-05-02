package orders

import java.time.LocalDate

class OrderService(private val dao: OrderDao) {
    fun findAll(): List<Order> = dao.findAll()

    fun createOrder(date: LocalDate, clientId: Int): Order = dao.createOrder(date, clientId)

    fun updateOrder(id: Int, date: LocalDate, clientId: Int): Order = dao.updateOrder(id, date, clientId)

    fun deleteOrder(id: Int) = dao.deleteOrder(id)
}