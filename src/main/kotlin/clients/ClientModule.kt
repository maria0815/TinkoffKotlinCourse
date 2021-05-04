@file:UseSerializers(LocalDateSerializer::class)

package clients

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton
import utils.LocalDateSerializer
import java.time.LocalDate

fun Application.clientModule() {
    val service: ClientService by closestDI().instance()

    routing {
        route("/clients") {
            get {
                call.respond(service.findAll())
            }
            post {
                val request = call.receive<CreateClientRequest>()
                call.respond(service.createClient(request.name, request.address))
            }
            put("{id}") {
                val id = call.parameters["id"]?.toInt()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }
                val request = call.receive<CreateClientRequest>()
                call.respond(service.updateClient(id, request.name, request.address))
            }
            delete("{id}") {
                val id = call.parameters["id"]?.toInt()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                call.respond(service.deleteClient(id))
            }
            get("{id}/listOfOrders") {
                val id = call.parameters["id"]?.toInt()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                call.respond(service.findAllOrdersByClientId(id))
            }
            put("{id}/addOrder") {
                val clientId = call.parameters["id"]?.toInt()
                if (clientId == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }
                val request = call.receive<AddOrderRequest>()
                call.respond(service.assignOrderToClient(request.orderId, request.orderDate, clientId))
            }
        }
    }
}

fun DI.Builder.clientComponents() {
    bind<ClientDao>() with singleton { ClientDao(instance()) }
    bind<ClientService>() with singleton { ClientService(instance()) }
}

@Serializable
private data class CreateClientRequest(val name: String, val address: String)

@Serializable
private data class AddOrderRequest(val orderId: Int, val orderDate: LocalDate)