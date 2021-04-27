package parallel_execution

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import parallel_execution.entity.ClientWithContract
import parallel_execution.exception.ClientNotFoundException
import parallel_execution.service.ClientService
import parallel_execution.service.ContractService

fun main() = runBlocking {
    val clientService = ClientService()
    val contractService = ContractService()

    for (id in 1..6) {
        try {
            coroutineScope {
                val client = async { clientService.findById(id) }
                val contract = async { contractService.findByClientId(id) }
                val clientWithContract = ClientWithContract(client.await(), contract.await())
                println(clientWithContract)
            }
        } catch (ex: ClientNotFoundException) {
            println(ex.message)
        }
    }
}
