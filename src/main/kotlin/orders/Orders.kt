package orders

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.*

object Orders : IntIdTable() {
    val date = date("date")
    val clientId = integer("clientId")
}
