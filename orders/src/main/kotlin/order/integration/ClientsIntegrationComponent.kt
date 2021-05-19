package order.integration

import order.configuration.ClientServiceInfo
import order.dao.OrderDaoImpl
import order.model.Client
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
class ClientsIntegrationComponent(private val clientServiceInfo: ClientServiceInfo) {
    private var logger = LoggerFactory.getLogger(ClientsIntegrationComponent::class.java)
    private val restTemplate: RestTemplate = RestTemplate()

    fun getClient(id: Int): Client? {
        return try {
            logger.debug("Getting client $id from remote service")
            restTemplate.getForObject("${clientServiceInfo.host}/clients/$id", Client::class.java)
        } catch (ex: HttpClientErrorException.NotFound) {
            logger.debug("Client $id not found")
            null
        }
    }
}