package dao

import entity.Client
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ClientDAOTest {

    @Test
    fun `method findAll returns a list of all clients`() {
        val listOfClients = listOf(
            Client(1, "Роснефть", "СПб"),
            Client(2, "Ростелеком", "МСК")
        )

        val result = ClientDAO().findAll()

        assertEquals(listOfClients, result)
    }
}