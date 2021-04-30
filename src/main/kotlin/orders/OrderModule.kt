package orders

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.orderModule() {

    val service = OrderService()
    routing {
        route("/orders") {
            get {
                call.respond(service.findAll())
            }
            post {
                call.respond("Заказ создан!")
            }
            put("{id}") {
                val id = call.parameters["id"]
                call.respond("Заказ с идентификатором $id изменен!")
            }
            delete("{id}") {
                val id = call.parameters["id"]
                call.respond("Заказ с идентификатором $id удален!")
            }
        }
    }
}