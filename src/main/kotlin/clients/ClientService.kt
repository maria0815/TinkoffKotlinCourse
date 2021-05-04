package clients

import orders.Order
import java.time.LocalDate

class ClientService(private val dao: ClientDao) {
    fun findAll(): List<Client> = dao.findAll()

    fun createClient(name: String, address: String): Client = dao.createClient(name, address)

    fun updateClient(id: Int, name: String, address: String): Client = dao.updateClient(id, name, address)

    fun deleteClient(id: Int) = dao.deleteClient(id)

    fun findAllOrdersByClientId(id: Int): List<Order> = dao.findAllOrdersByClientId(id)

    fun assignOrderToClient(orderId: Int, orderDate: LocalDate, clientId: Int): Order =
        dao.assignOrderToClient(orderId, orderDate, clientId)
}