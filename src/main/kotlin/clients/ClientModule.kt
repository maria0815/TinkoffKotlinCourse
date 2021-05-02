package clients

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.closestDI
import org.kodein.di.singleton

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
                val id = call.parameters["id"]
                call.respond("Заказы по клиенту $id")
            }
            put("{id}/addOrder") {
                val orderId = call.parameters["id"]
                call.respond("")
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