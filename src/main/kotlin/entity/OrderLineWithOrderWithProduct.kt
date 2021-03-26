package entity

import java.sql.ResultSet

data class OrderLineWithOrderWithProduct(
    val orderLinePrice: Int,
    val productName: String,
    val orderDate: String,
)

class OrderLineWithOrderWithProductMapper : Mapper<OrderLineWithOrderWithProduct> {
    override fun writeResultSetToClass(resultSet: ResultSet): OrderLineWithOrderWithProduct {
        val orderLinePrice = resultSet.getInt("price")
        val productName = resultSet.getString("name")
        val orderDate = resultSet.getString("date")

        return OrderLineWithOrderWithProduct(orderLinePrice, productName, orderDate)
    }
}