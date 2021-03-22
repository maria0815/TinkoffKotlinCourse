package entity

import java.sql.ResultSet

data class Order(
    val id: Int,
    val client_id: Int,
    val date: String,
    val delivery: Boolean,
    val archive: Boolean,
)

class OrderMapper : Mapper<Order> {
    override fun writeResultSetToClass(resultSet: ResultSet): Order {
        val id = resultSet.getInt("id")
        val clientId = resultSet.getInt("client_id")
        val date = resultSet.getString("date")
        val delivery = resultSet.getBoolean("delivery")
        val archive = resultSet.getBoolean("archive")

        return Order(id, clientId, date, delivery, archive)
    }
}