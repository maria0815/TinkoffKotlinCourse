package order.dao

import order.model.Order
import org.slf4j.LoggerFactory
import java.sql.ResultSet
import javax.sql.DataSource

class OrderDaoImpl(private val dataSource: DataSource) : OrderDao {
    private var logger = LoggerFactory.getLogger(OrderDaoImpl::class.java)

    override fun getById(id: Int): Order? {
        logger.debug("Looking for order $id")
        var order: Order? = null
        dataSource.connection.use { connection ->
            connection.prepareStatement("select top 1 id, date, clientId from [Order] where id = ?").use { statement ->
                statement.setInt(1, id)
                statement.executeQuery().use { result ->
                    while (result.next()) {
                        logger.debug("Order $id found")
                        order = getOrder(result)
                    }
                }
            }
        }
        return order
    }

    override fun getAll(): List<Order> {
        val list = mutableListOf<Order>()
        logger.debug("Looking for all orders")
        dataSource.connection.use { connection ->
            connection.prepareStatement("select id, date, clientId from [Order]").use { statement ->
                statement.executeQuery().use { result ->
                    while (result.next()) {
                        val order = getOrder(result)
                        list.add(order)
                    }
                }
            }
        }
        logger.debug("Found ${list.size} orders")
        return list
    }

    private fun getOrder(result: ResultSet): Order {
        val id = result.getInt("id")
        val date = result.getDate("date").toLocalDate()
        val clientId = result.getInt("clientId")

        return Order(id, date, clientId)
    }
}