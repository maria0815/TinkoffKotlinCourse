import utils.DbConnection
import utils.DbInitializer
import service.ClientService
import service.OrderLineService
import service.OrderService
import service.ProductService
import java.time.LocalDate

fun main() {
    DbConnection().use { dbConnection ->
        val dbInitializer = DbInitializer(dbConnection)
        dbInitializer.setup()

        val clientService = ClientService(dbConnection)
        val orderService = OrderService(dbConnection)
        val productService = ProductService(dbConnection)
        val orderLinesService = OrderLineService(dbConnection)

        println("--------------------КЛИЕНТЫ----------------------")

        println("Список клиентов, у которых идентификатор равен 1:")
        clientService.findClientById(1)?.let { println(it) }

        println("\nСписок клиентов, у которых город не СПБ:")
        clientService.findClientWithCityIsNot("Санкт-Петербург").forEach { println(it) }

        println("\nГруппировка клиентов по городам:")
        clientService.groupClientsByCity().forEach { println(it) }

        println("\nСортировка клиентов по убыванию идетификатора:")
        clientService.sortClientsInDescending().forEach { println(it) }

        println("\nСписок с информацией о номере заказа, дате заказа и наименовании клиента:")
        clientService.joinClientWithOrder().forEach { println(it) }

        println("\n--------------------ЗАКАЗЫ----------------------")

        println("Список заказов, у которых идентификатор равен 5:")
        orderService.findOrderById(5)?.let { println(it) }

        println("\nСписок заказов с датой позднее 4.03.21:")
        val date = LocalDate.of(2021, 3, 4)
        orderService.findOrderWithDateLater(date).forEach { println(it) }

        println("\nСписок заказов, группированных по ИД клиента:")
        orderService.groupOrdersByClientId().forEach { println(it) }

        println("\nСписок заказов, сортированных по убыванию :")
        orderService.sortOrdersInDescending().forEach { println(it) }

        println("--------------------СТРОКИ ЗАКАЗОВ----------------------")

        println("\nСписок строк заказа, у которых ИД равен 10 :")
        orderLinesService.findOrderLineById(10)?.let { println(it) }

        println("\nСписок строк заказа, у которых цена больше 60:")
        orderLinesService.findOrderLineWithPriceGraterThen(60).forEach { println(it) }

        println("\nСписок строк заказа, группированных по ИД продукта:")
        orderLinesService.findOrderLinesWithProductCountGraterThen(9).forEach { println(it) }

        println("\nСписок строк заказа, сортиованных по убыванию скидки:")
        orderLinesService.sortOrderLinesInDescendingOfDiscount().forEach { println(it) }

        println("\nСписок строк заказа с информацией о продукте и заказчике :")
        orderLinesService.joinOrderLineWithProductWithOrder().forEach { println(it) }

        println("--------------------ПРОДУКТЫ----------------------")

        println("\nСписок продуктов, у которых идентификатор равен 2:")
        productService.findProductsWithIdIs2(2)?.let { println(it) }

        println("\nСписок продуктов, у которых идентификатор больше 1:")
        productService.findProductsByIdGrater(1).forEach { println(it) }

        println("\nСписок продуктов, группированныых по произволителю:")
        productService.groupProductsByManufacturer().forEach { println(it) }

        println("\nСписок продуктов, группированныых по произволителю:")
        productService.sortProductsInDescendingOfId().forEach { println(it) }

        dbInitializer.tearDown()
    }
}