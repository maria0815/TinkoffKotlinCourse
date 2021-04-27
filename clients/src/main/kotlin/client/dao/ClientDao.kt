package client.dao

import client.model.Client
import org.springframework.stereotype.Repository

@Repository
interface ClientDao {
    fun getById(id: Int): Client?
}