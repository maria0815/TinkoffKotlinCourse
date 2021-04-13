package client.dao

import client.model.Client

class ClientDaoImpl : ClientDao {
    val clients = listOf(
        Client(1, "Клиент 1", "Адресс клиента 1"),
        Client(2, "Клиент 2", "Адресс клиента 2"),
        Client(3, "Клиент 3", "Адресс клиента 3"),
        Client(4, "Клиент 4", "Адресс клиента 4"),
        Client(5, "Клиент 5", "Адресс клиента 5")
    )

    override fun getById(id: Int): Client? = clients.firstOrNull { it.id == id }
}