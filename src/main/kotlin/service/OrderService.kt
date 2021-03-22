package service

import utils.DbConnection
import entity.*
import script.Script
import java.sql.SQLException

class OrderService(private val dbConnection: DbConnection) {

    /**
     *Возвращает заказ с идентификатором равным [id]
     */
    fun findOrderById(id: Int): Order? {
        return try {
            dbConnection.executeQueryForObject(Script.SELECT_ORDER_WITH_ID, OrderMapper(), id)
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            null
        }
    }

    /**
     *Возвращает список заказов, у которых дата позднее, чем 04.03.21
     */
    fun findOrderWithDateLater(): List<Order> {
        return try {
            dbConnection.executeQuery(Script.SELECT_ORDERS_WHERE_DATE_AFTER, OrderMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     *Возвращает список с количеством заказов по каждому клиенту
     */
    fun groupOrdersByClientId(): List<OrderGroupedByClientId> {
        return try {
            dbConnection.executeQuery(Script.GROUP_ORDERS_BY_CLIENTID, OrderGroupedByClientIdMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     *Возвращает список заказов, отсортированных по убыванию даты заказа
     */
    fun sortOrdersInDescending(): List<Order> {
        return try {
            dbConnection.executeQuery(Script.SORT_ORDERS_BY_DATE_DESC, OrderMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }
}