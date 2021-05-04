package clients

import org.jetbrains.exposed.dao.id.IntIdTable

object Clients : IntIdTable() {
    val name = text("name")
    val address = text("address")
}