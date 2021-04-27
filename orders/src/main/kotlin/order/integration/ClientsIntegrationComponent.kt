package order.integration

import order.model.Client
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Component
class ClientsIntegrationComponent {

    private val restTemplate: RestTemplate = RestTemplate()

    fun getClient(id: Int): Client? {
        return try {
            restTemplate.getForObject("http://localhost:8080/clients/$id", Client::class.java)
        } catch (ex: HttpClientErrorException.NotFound) {
            null
        }
    }
}