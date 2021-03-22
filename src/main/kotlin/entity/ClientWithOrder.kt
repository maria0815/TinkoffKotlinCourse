package entity

import java.sql.ResultSet

data class ClientWithOrder(
    val orderId: Int,
    val orderDate: String,
    val clientName: String,
)

class ClientWithOrderMapper : Mapper<ClientWithOrder> {
    override fun writeResultSetToClass(resultSet: ResultSet): ClientWithOrder {
        val id = resultSet.getInt("id")
        val date = resultSet.getString("date")
        val name = resultSet.getString("name")

        return ClientWithOrder(id, date, name)
    }
}