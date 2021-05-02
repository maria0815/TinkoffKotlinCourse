package orders

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.LocalDate

class OrderDao(private val database: Database) {
    fun findAll() = transaction(database) {
        Orders.selectAll().map(::extractOrder)
    }

    fun createOrder(date: LocalDate, clientId: Int): Order = transaction(database) {
        val id = Orders.insertAndGetId {
            it[Orders.date] = date
            it[Orders.clientId] = clientId
        }
        Order(id.value, date, clientId)
    }

    fun updateOrder(id: Int, date: LocalDate, clientId: Int): Order = transaction(database) {
        Orders.update({ Orders.id eq id }) {
            it[Orders.date] = date
            it[Orders.clientId] = clientId
        }
        Order(id, date, clientId)
    }

    fun deleteOrder(id: Int) = transaction(database) {
        Orders.deleteWhere { Orders.id eq id }
    }

    private fun extractOrder(row: ResultRow): Order = Order(
        row[Orders.id].value,
        row[Orders.date],
        row[Orders.clientId]
    )
}