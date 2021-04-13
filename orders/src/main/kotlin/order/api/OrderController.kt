package order.api

import order.model.Order
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import order.dao.OrderDao
import order.integration.ClientsIntegrationComponent
import order.model.OrderWithClient
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("orders")
class OrderController(
    private val orderDao: OrderDao,
    private val clientsService: ClientsIntegrationComponent
) {

    @ApiOperation("Возвращает все заказы")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Все хорошо", response = Order::class)
        ]
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrders(): ResponseEntity<List<Order>> {
        return ResponseEntity.ok(orderDao.getAll())
    }

    @ApiOperation("Возвращает заказ с указанным id")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "Заказ найден", response = OrderWithClient::class),
            ApiResponse(code = 404, message = "Заказ не найден")
        ]
    )
    @GetMapping(value = ["{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrder(
        @ApiParam("Идентификатор заказа")
        @PathVariable id: Int
    ): ResponseEntity<OrderWithClient> {
        val order = orderDao.getById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val client = clientsService.getClient(order.clientId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(OrderWithClient(order, client))
    }


    @ApiOperation("Создает новый заказ")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Заказ создан"),
            ApiResponse(code = 400, message = "Переданный заказ имеет не корректный формат")
        ]
    )
    @PostMapping
    fun addOrder(
        @ApiParam("Создаваемый заказ")
        @RequestBody order: Order
    ) {
        println("Заказ создан. $order")
    }

    @ApiOperation("Обновляет заказ с указанным id")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Заказ обновлен"),
            ApiResponse(code = 400, message = "Переданный заказ имеет не корректный формат"),
            ApiResponse(code = 404, message = "Заказ не найден")
        ]
    )
    @PutMapping("/{id}")
    fun updateOrder(
        @ApiParam("Идентификатор заказа")
        @PathVariable id: String,
        @ApiParam("Информация о заказе")
        @RequestBody order: Order
    ) {
        println("Заказ был обновлен. Заказ = $order, id = $id")
    }

    @ApiOperation("Удаляет заказ с указанным id")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "Заказ удален"),
            ApiResponse(code = 404, message = "Заказ не найден")
        ]
    )
    @DeleteMapping("/{id}")
    fun deleteOrder(
        @ApiParam("Идентификатор заказа")
        @PathVariable id: String
    ) {
        println("Заказ с id = $id был удален")
    }
}