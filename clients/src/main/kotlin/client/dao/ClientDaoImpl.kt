package client.dao

import client.model.Client
import java.sql.ResultSet
import javax.sql.DataSource

class ClientDaoImpl(private val dataSource: DataSource) : ClientDao {
    override fun getById(id: Int): Client? {
        dataSource.connection.use {
            val statement = it.prepareStatement("select id, name, address from client where id = ?")
            statement.setInt(1, id)
            val result = statement.executeQuery()
            if (!result.isBeforeFirst) return null
            result.next()
            return getClient(result)
        }
    }

    private fun getClient(result: ResultSet): Client {
        val id = result.getInt("id")
        val name = result.getString("name")
        val address = result.getString("address")

        return Client(id, name, address)
    }
}