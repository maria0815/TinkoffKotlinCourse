package dao

import entity.Client

class ClientDAO {

    private val listOfClients = listOf(
        Client(1, "Роснефть", "СПб"),
        Client(2, "Ростелеком", "МСК"),
    )

    /**
     * Возвращает список всех клиентов.
     */
    fun findAll(): List<Client> = listOfClients

    /**
     * Возвращает клиента, у которого идентификатор равен [clientId].
     */
    fun findByClientId(clientId: Int): Client = listOfClients.first { it.id == clientId }
}