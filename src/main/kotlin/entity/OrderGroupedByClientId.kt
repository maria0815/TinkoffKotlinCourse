package entity

import java.sql.ResultSet

data class OrderGroupedByClientId(
    val clientId: Int,
    val order_count: Int,
)

class OrderGroupedByClientIdMapper : Mapper<OrderGroupedByClientId> {
    override fun writeResultSetToClass(resultSet: ResultSet): OrderGroupedByClientId {
        val clientId = resultSet.getInt("client_id")
        val orderCountDownLatch = resultSet.getInt("order_count")

        return OrderGroupedByClientId(clientId, orderCountDownLatch)
    }
}