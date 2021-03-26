package entity

import java.sql.Date
import java.sql.ResultSet

data class Order(
    val id: Int,
    val client_id: Int,
    val date: Date,
    val delivery: Boolean,
    val archive: Boolean,
)

class OrderMapper : Mapper<Order> {
    override fun writeResultSetToClass(resultSet: ResultSet): Order {
        val id = resultSet.getInt("id")
        val clientId = resultSet.getInt("client_id")
        val date = resultSet.getDate("date")
        val delivery = resultSet.getBoolean("delivery")
        val archive = resultSet.getBoolean("archive")

        return Order(id, clientId, date, delivery, archive)
    }
}