package service

import entity.*
import utils.DbConnection
import script.Script
import java.sql.SQLException

class ClientService(private val dbConnection: DbConnection) {

    /**
     * Возвращает клиента с идентификатором равным [id]
     */
    fun findClientById(id: Int): Client? {
        return try {
            dbConnection.executeQueryForObject(Script.SELECT_CLIENT_WITH_ID, ClientMapper(), id)
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            null
        }
    }

    /**
     * Возвращает список клиентов, у которых город не СПБ
     */
    fun findClientWithCityIsNotSpb(): List<Client> {
        return try {
            dbConnection.executeQuery(Script.SELECT_CLIENTS_WHERE_CITY_NO_EQUALS_SPB, ClientMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     *Возвращает список с количеством клиентов в каждом городе
     */
    fun groupClientsByCity(): List<ClientsInCity> {
        return try {
            dbConnection.executeQuery(Script.GROUP_CLIENTS_BY_CITY, ClientsInCityMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     *Возвращает список клиентов, отсортированных по убыванию id
     */
    fun sortClientsInDescending(): List<Client> {
        return try {
            dbConnection.executeQuery(Script.SORT_CLIENTS_BY_ID_DESC, ClientMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     *Возвращает список с информацией о клиенте и заказе
     */
    fun joinClientWithOrder(): List<ClientWithOrder> {
        return try {
            dbConnection.executeQuery(Script.SELECT_INFO_OF_CLIENTS_WITH_ORDERS, ClientWithOrderMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }
}