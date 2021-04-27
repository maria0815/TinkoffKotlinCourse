package order.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

@ApiModel(description = "Заказ")
data class OrderWithClient(
    @ApiModelProperty(value = "Идентификатор заказа")
    val id: Int,
    @ApiModelProperty(value = "Дата заказа")
    val date: LocalDate,
    @ApiModelProperty(value = "Имя клиента")
    val clientName: String,
    @ApiModelProperty(value = "Адрес клиента")
    val clientAddress: String
) {
    constructor(order: Order, client: Client) : this(order.id, order.date, client.name, client.address)
}