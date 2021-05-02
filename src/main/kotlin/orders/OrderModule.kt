@file:UseSerializers(LocalDateSerializer::class)
package orders

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.jetbrains.exposed.sql.Database
import utils.LocalDateSerializer
import java.time.LocalDate

fun Application.orderModule(database: Database) {
    val dao = OrderDao(database)
    val service = OrderService(dao)
    routing {
        route("/orders") {
            get {
                call.respond(service.findAll())
            }
            post {
                val request = call.receive<CreateOrderRequest>()
                call.respond(service.createOrder(request.date, request.clientId))
                call.respond("Заказ создан!")
            }
            put("{id}") {
                val id = call.parameters["id"]!!.toInt()
                val request = call.receive<CreateOrderRequest>()
                call.respond(service.updateOrder(id, request.date, request.clientId))
                call.respond("Заказ с идентификатором $id изменен!")
            }
            delete("{id}") {
                val id = call.parameters["id"]!!.toInt()
                call.respond(service.deleteOrder(id))
                call.respond("Заказ с идентификатором $id удален!")
            }
        }
    }
}

@Serializable
private data class CreateOrderRequest(val date: LocalDate, val clientId: Int)
