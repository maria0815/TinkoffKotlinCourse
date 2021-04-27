package parallel_execution.service

import kotlinx.coroutines.delay
import parallel_execution.entity.Client
import parallel_execution.exception.ClientNotFoundException

/**
 * Сервис для работы с клиентами.
 */
class ClientService {
    private val listOfClients = listOf(
        Client(1, "Роснефть", "СПб"),
        Client(2, "Ростелеком", "МСК"),
        Client(3, "Газпром", "СПб"),
        Client(4, "Урал", "МСК"),
    )

    /**
     * Возвращает клиента, у которого идентификатор равен [clientId].
     */
    suspend fun findById(clientId: Int): Client {
        delay(1000L)
        return listOfClients.firstOrNull { it.id == clientId }
            ?: throw ClientNotFoundException("Клиент с идентификатором $clientId не найден")
    }
}