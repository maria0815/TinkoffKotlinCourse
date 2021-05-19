package order.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ConfigProperties {
    @Bean
    @ConfigurationProperties(prefix = "client-service")
    fun clientServiceInfo(): ClientServiceInfo = ClientServiceInfo()
}

class ClientServiceInfo {
    var host: String? = null
}