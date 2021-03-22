package entity

import java.sql.ResultSet

data class Client(
    val id: Int,
    val name: String,
    val email: String,
    val city: String,
)

class ClientMapper : Mapper<Client> {
    override fun writeResultSetToClass(resultSet: ResultSet): Client {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val email = resultSet.getString("email")
        val city = resultSet.getString("city")

        return Client(id, name, email, city)
    }
}