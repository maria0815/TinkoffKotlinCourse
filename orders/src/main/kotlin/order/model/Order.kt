package order.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate

@ApiModel(description = "Заказ")
data class Order(
    @ApiModelProperty(value = "Идентификатор заказа")
    val id: Int,
    @ApiModelProperty(value = "Дата заказа")
    val date: LocalDate,
    @ApiModelProperty(value = "Идентификатор клиента")
    val clientId: Int
)