package clients

import orders.Order
import orders.OrderDao.Companion.extractOrder
import orders.Orders
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.LocalDate

class ClientDao(private val database: Database) {
    fun findAll() = transaction(database) {
        Clients.selectAll().map(::extractClient)
    }

    fun createClient(name: String, address: String): Client = transaction(database) {
        val id = Clients.insertAndGetId {
            it[Clients.name] = name
            it[Clients.address] = address
        }
        Client(id.value, name, address)
    }

    fun updateClient(id: Int, name: String, address: String): Client = transaction(database) {
        Clients.update({ Clients.id eq id }) {
            it[Clients.name] = name
            it[Clients.address] = address
        }
        Client(id, name, address)
    }

    fun deleteClient(id: Int) = transaction(database) {
        Clients.deleteWhere { Clients.id eq id }
    }

    fun findAllOrdersByClientId(id: Int) = transaction(database) {
        Orders.select { Orders.clientId eq id }.map(::extractOrder)
    }

    fun assignOrderToClient(orderId: Int, orderDate: LocalDate, clientId: Int): Order = transaction(database) {
        Orders.update({ Orders.id eq orderId }) {
            it[date] = orderDate
            it[Orders.clientId] = clientId
        }
        Order(orderId, orderDate, clientId)
    }

    companion object {
        fun extractClient(row: ResultRow): Client = Client(
            row[Clients.id].value,
            row[Clients.name],
            row[Clients.address]
        )
    }
}