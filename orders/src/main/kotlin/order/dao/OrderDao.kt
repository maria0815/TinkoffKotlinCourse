package order.dao

import order.model.Order
import org.springframework.stereotype.Repository

@Repository
interface OrderDao {
    fun getById(id: Int): Order?
    fun getAll(): List<Order>
}