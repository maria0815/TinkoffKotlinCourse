package service

import utils.DbConnection
import entity.Order
import entity.OrderGroupedByClientId
import entity.OrderGroupedByClientIdMapper
import entity.OrderMapper
import script.Script
import java.time.LocalDate

class OrderService(private val dbConnection: DbConnection) {

    /**
     *Возвращает заказ с идентификатором равным [id]
     */
    fun findOrderById(id: Int): Order? {
        return runCatching {
            dbConnection.executeQueryForObject(Script.SELECT_ORDER_WITH_ID, OrderMapper(), id)
        }.onFailure { println("Ошибка выполнения запроса. ${it.message}") }.getOrNull()
    }

    /**
     *Возвращает список заказов, у которых дата позднее, чем [date]
     */
    fun findOrderWithDateLater(date: LocalDate): List<Order> {
        return runCatching {
            dbConnection.executeQuery(Script.SELECT_ORDERS_WHERE_DATE_AFTER, OrderMapper(), date)
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     *Возвращает список с количеством заказов по каждому клиенту
     */
    fun groupOrdersByClientId(): List<OrderGroupedByClientId> {
        return runCatching {
            dbConnection.executeQuery(Script.GROUP_ORDERS_BY_CLIENTID, OrderGroupedByClientIdMapper())
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     *Возвращает список заказов, отсортированных по убыванию даты заказа
     */
    fun sortOrdersInDescending(): List<Order> {
        return runCatching {
            dbConnection.executeQuery(Script.SORT_ORDERS_BY_DATE_DESC, OrderMapper())
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }
}