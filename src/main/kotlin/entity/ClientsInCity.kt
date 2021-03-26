package entity

import java.sql.ResultSet

data class ClientsInCity(
    val city: String,
    val clientsCount: String,
)

class ClientsInCityMapper : Mapper<ClientsInCity> {
    override fun writeResultSetToClass(resultSet: ResultSet): ClientsInCity {
        val city = resultSet.getString("city")
        val cityCount = resultSet.getString("city_count")

        return ClientsInCity(city, cityCount)
    }
}