package parallel_execution.service

import kotlinx.coroutines.delay
import parallel_execution.entity.Contract

/**
 * Сервис для работы с договорами.
 */
class ContractService {
    private val listOfContracts = listOf(
        Contract(1, 2, "Договор поручительства"),
        Contract(2, 4, "Договор лицензионный"),
    )

    /**
     * Возвращает договор, для клиента с индентификатором [clientId].
     */
    suspend fun findByClientId(clientId: Int): Contract? {
        delay(1000L)
        return listOfContracts.firstOrNull { it.clientId == clientId }
    }
}