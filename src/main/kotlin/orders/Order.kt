@file:UseSerializers(LocalDateSerializer::class)

package orders

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import utils.LocalDateSerializer
import java.time.LocalDate


@Serializable
data class Order(val id: Int, val date: LocalDate, val clientId: Int)