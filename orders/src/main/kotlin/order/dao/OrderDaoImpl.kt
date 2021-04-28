package order.dao

import order.model.Order
import org.slf4j.LoggerFactory
import java.sql.ResultSet
import javax.sql.DataSource

class OrderDaoImpl(private val dataSource: DataSource) : OrderDao {
    private var logger = LoggerFactory.getLogger(OrderDaoImpl::class.java)

    override fun getById(id: Int): Order? {
        logger.debug("Looking for order $id")
        dataSource.connection.use {
            val statement = it.prepareStatement("select id, date, clientId from [Order] where id = ?")
            statement.setInt(1, id)
            val result = statement.executeQuery()
            if (!result.isBeforeFirst) {
                logger.debug("Order $id not found")
                return null
            }
            logger.debug("Order $id found")
            result.next()
            return getOrder(result)
        }
    }

    override fun getAll(): List<Order> {
        val list = mutableListOf<Order>()
        logger.debug("Looking for all orders")
        dataSource.connection.use {
            val statement = it.prepareStatement("select id, date, clientId from [Order]")
            val result = statement.executeQuery()
            while (result.next()) {
                val order = getOrder(result)
                list.add(order)
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