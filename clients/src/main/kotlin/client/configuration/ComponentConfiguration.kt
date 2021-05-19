package client.configuration

import client.dao.ClientDao
import client.dao.ClientDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class ComponentConfiguration {

    @Bean
    fun clientDao(dataSource: DataSource): ClientDao {
        return ClientDaoImpl(dataSource)
    }
}