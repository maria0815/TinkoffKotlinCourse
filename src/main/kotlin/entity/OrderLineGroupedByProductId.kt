package entity

import java.sql.ResultSet

data class OrderLineGroupedByProductId(
    val product_id: Int,
    val count: Int,
)

class OrderLineGroupedByProductIdMapper : Mapper<OrderLineGroupedByProductId> {
    override fun writeResultSetToClass(resultSet: ResultSet): OrderLineGroupedByProductId {
        val productId = resultSet.getInt("product_id")
        val count = resultSet.getInt("count")

        return OrderLineGroupedByProductId(productId, count)
    }
}