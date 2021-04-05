package parallel_execution.entity

/**
 * Хранит информацию о клиенте и договоре.
 */
data class ClientWithContract(
    val clientId: Int,
    val clientName: String,
    val city: String,
    val contractId: Int?,
    val contractDescription: String?
) {
    constructor(client: Client, contract: Contract?) : this(
        client.id,
        client.name,
        client.city,
        contract?.id,
        contract?.description
    )
}