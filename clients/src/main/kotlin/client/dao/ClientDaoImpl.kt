package client.dao

import client.model.Client
import org.slf4j.LoggerFactory
import java.sql.ResultSet
import javax.sql.DataSource

class ClientDaoImpl(private val dataSource: DataSource) : ClientDao {
    private var logger = LoggerFactory.getLogger(ClientDaoImpl::class.java)


    override fun getById(id: Int): Client? {
        var client: Client? = null
        dataSource.connection.use { connection ->
            logger.debug("Looking for client $id")
            connection.prepareStatement("select id, name, address from client where id = ? limit 1").use { statement ->
                statement.setInt(1, id)
                statement.executeQuery().use { result ->
                    while (result.next()) {
                        logger.debug("Client $id found")
                        client = getClient(result)
                    }
                }
            }
        }
        return client
    }

    private fun getClient(result: ResultSet): Client {
        val id = result.getInt("id")
        val name = result.getString("name")
        val address = result.getString("address")

        return Client(id, name, address)
    }
}