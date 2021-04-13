package client.configuration

import client.dao.ClientDao
import client.dao.ClientDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ComponentConfiguration {

    @Bean
    fun clientDao(): ClientDao {
        return ClientDaoImpl()
    }
}