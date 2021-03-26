package entity

import java.sql.ResultSet

data class OrderLine(
    val id: Int,
    val order_id: Int,
    val product_id: Int,
    val price: Int,
    val quantity: Int,
    val discount: Int,
)

class OrderLineMapper : Mapper<OrderLine> {
    override fun writeResultSetToClass(resultSet: ResultSet): OrderLine {
        val id = resultSet.getInt("id")
        val orderId = resultSet.getInt("order_id")
        val productId = resultSet.getInt("product_id")
        val price = resultSet.getInt("price")
        val quantity = resultSet.getInt("quantity")
        val discount = resultSet.getInt("discount")

        return OrderLine(id, orderId, productId, price, quantity, discount)
    }
}