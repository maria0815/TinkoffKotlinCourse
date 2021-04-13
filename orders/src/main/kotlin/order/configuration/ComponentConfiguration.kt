package order.configuration

import order.dao.OrderDao
import order.dao.OrderDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ComponentConfiguration {

    @Bean
    fun clientDao(): OrderDao {
        return OrderDaoImpl()
    }
}