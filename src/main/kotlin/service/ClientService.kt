package service

import entity.ClientWithOrder
import entity.ClientWithOrderMapper
import entity.Client
import entity.ClientMapper
import entity.ClientsInCity
import entity.ClientsInCityMapper
import utils.DbConnection
import script.Script

class ClientService(private val dbConnection: DbConnection) {

    /**
     * Возвращает клиента с идентификатором равным [id]
     */
    fun findClientById(id: Int): Client? {
        return runCatching {
            dbConnection.executeQueryForObject(Script.SELECT_CLIENT_WITH_ID, ClientMapper(), id)
        }.onFailure { println("Ошибка выполнения запроса. ${it.message}") }.getOrNull()
    }

    /**
     * Возвращает список клиентов, у которых город не СПБ
     */
    fun findClientWithCityIsNot(city: String): List<Client> {
        return runCatching {
            dbConnection.executeQuery(Script.SELECT_CLIENTS_WHERE_CITY_NO_EQUALS, ClientMapper(), city)
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     *Возвращает список с количеством клиентов в каждом городе
     */
    fun groupClientsByCity(): List<ClientsInCity> {
        return runCatching {
            dbConnection.executeQuery(Script.GROUP_CLIENTS_BY_CITY, ClientsInCityMapper())
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     *Возвращает список клиентов, отсортированных по убыванию id
     */
    fun sortClientsInDescending(): List<Client> {
        return runCatching {
            dbConnection.executeQuery(Script.SORT_CLIENTS_BY_ID_DESC, ClientMapper())
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     *Возвращает список с информацией о клиенте и заказе
     */
    fun joinClientWithOrder(): List<ClientWithOrder> {
        return runCatching {
            dbConnection.executeQuery(Script.SELECT_INFO_OF_CLIENTS_WITH_ORDERS, ClientWithOrderMapper())
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }
}