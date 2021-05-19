package client.model

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(description = "Клиент")
data class Client(
    @ApiModelProperty(value = "Идентификатор клиента")
    val id: Int,
    @ApiModelProperty(value = "Название клиента")
    val name: String,
    @ApiModelProperty(value = "Адрес клиента")
    val address: String
)