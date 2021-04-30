package clients

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Database

fun Application.clientModule(database: Database) {
    val dao = ClientDao(database)
    val service = ClientService(dao)
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
                val id = call.parameters["id"]!!.toInt()
                val request = call.receive<CreateClientRequest>()
                call.respond(service.updateClient(id, request.name, request.address))
            }
            delete("{id}") {
                val id = call.parameters["id"]!!.toInt()
                call.respond(service.deleteClient(id))
            }
            get("/listOfOrdersByClientID/{id}") {
                val id = call.parameters["id"]
                call.respond("Заказы по клиенту $id")
            }
            put("/orders/{orderId}") {
                val orderId = call.parameters["orderId"]
                call.respond("У заказа с идентификатором $orderId изменен клиент на ...!")
            }
        }
    }
}

@Serializable
private data class CreateClientRequest(val name: String, val address: String)