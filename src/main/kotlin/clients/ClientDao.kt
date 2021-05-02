package clients

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

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

    private fun extractClient(row: ResultRow): Client = Client(
            row[Clients.id].value,
            row[Clients.name],
            row[Clients.address]
    )
}