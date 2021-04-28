package order.dao

import order.model.Order
import java.sql.ResultSet
import java.time.LocalDate
import javax.sql.DataSource

class OrderDaoImpl(private val dataSource: DataSource) : OrderDao {
    override fun getById(id: Int): Order? {
        dataSource.connection.use {
            val statement = it.prepareStatement("select id, date, clientId from order where id = ?")
            statement.setInt(1, id)
            val result = statement.executeQuery()
            if (!result.isBeforeFirst) return null
            result.next()
            return getOrder(result)
        }
    }

    override fun getAll(): List<Order> {
        val list = mutableListOf<Order>()

        dataSource.connection.use {
            val statement = it.prepareStatement("select id, date, clientId from order")
            val result = statement.executeQuery()
            while (result.next()) {
                val order = getOrder(result)
                list.add(order)
            }
        }
        return list
    }

    private fun getOrder(result: ResultSet): Order {
        val id = result.getInt("id")
        val date = result.getDate("date").toLocalDate()
        val clientId = result.getInt("clientId")

        return Order(id, date, clientId)
    }
}