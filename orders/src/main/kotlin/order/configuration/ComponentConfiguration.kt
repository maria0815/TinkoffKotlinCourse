package order.configuration

import order.dao.OrderDao
import order.dao.OrderDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class ComponentConfiguration {

    @Bean
    fun clientDao(dataSource: DataSource): OrderDao {
        return OrderDaoImpl(dataSource)
    }
}