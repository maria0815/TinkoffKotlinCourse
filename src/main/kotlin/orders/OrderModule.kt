@file:UseSerializers(LocalDateSerializer::class)

package orders

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.kodein.di.singleton
import utils.LocalDateSerializer
import java.time.LocalDate
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI


fun Application.orderModule() {
    val service: OrderService by closestDI().instance()

    routing {
        route("/orders") {
            get {
                call.respond(service.findAll())
            }
            post {
                val request = call.receive<CreateOrderRequest>()
                call.respond(service.createOrder(request.date, request.clientId))
            }
            put("{id}") {
                val id = call.parameters["id"]?.toInt()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }
                val request = call.receive<CreateOrderRequest>()
                call.respond(service.updateOrder(id, request.date, request.clientId))
            }
            delete("{id}") {
                val id = call.parameters["id"]?.toInt()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                call.respond(service.deleteOrder(id))
            }
        }
    }
}

fun DI.Builder.orderComponents() {
    bind<OrderDao>() with singleton { OrderDao(instance()) }
    bind<OrderService>() with singleton { OrderService(instance()) }
}

@Serializable
private data class CreateOrderRequest(val date: LocalDate, val clientId: Int)
