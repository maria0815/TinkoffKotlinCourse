package clients

class ClientService(private val dao: ClientDao) {
    fun findAll(): List<Client> = dao.findAll()

    fun createClient(name: String, address: String): Client = dao.createClient(name, address)

    fun updateClient(id: Int, name: String, address: String): Client = dao.updateClient(id, name, address)

    fun deleteClient(id: Int) = dao.deleteClient(id)
}