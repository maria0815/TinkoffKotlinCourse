package order.integration

import order.configuration.ClientServiceInfo
import order.model.Client
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
class ClientsIntegrationComponent(private val clientServiceInfo: ClientServiceInfo) {

    private val restTemplate: RestTemplate = RestTemplate()

    fun getClient(id: Int): Client? {
        return try {
            restTemplate.getForObject("${clientServiceInfo.host}/clients/$id", Client::class.java)
        } catch (ex: HttpClientErrorException.NotFound) {
            null
        }
    }
}