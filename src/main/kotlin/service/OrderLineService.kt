package service

import utils.DbConnection
import entity.*
import script.Script
import java.sql.SQLException

class OrderLineService(private val dbConnection: DbConnection) {
    /**
     * Возвращает строку заказа с [id], переданным на вход
     **/
    fun findOrderLineById(id: Int): OrderLine? {
        return try {
            dbConnection.executeQueryForObject(Script.SELECT_ORDER_LINE_WHERE_ID, OrderLineMapper(), id)

        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            null
        }
    }

    /**
     * Возвращает список строк заказов, у которых цена больше, чем [price]
     **/
    fun findOrderLineWithPriceGraterThen(price: Int): List<OrderLine> {
        return try {
            dbConnection.executeQuery(Script.SELECT_ORDERS_LINE_WHERE_PRICE_GRATER, OrderLineMapper(), price)
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     * Возвращает список строк заказов, в которых заказано больше, чем [productCount] единиц
     **/
    fun findOrderLinesWithProductCountGraterThen(productCount: Int): List<OrderLineGroupedByProductId> {
        return try {
            dbConnection.executeQuery(Script.GROUP_ORDERS_LINE_BY_PRODUCT_ID,
                OrderLineGroupedByProductIdMapper(),
                productCount)

        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     * Возвращает список строк заказов, отсортированных по убыванию скидки
     **/
    fun sortOrderLinesInDescendingOfDiscount(): List<OrderLine> {
        return try {
            dbConnection.executeQuery(Script.SORT_ORDERS_LINE_BY_DISCOUNT_DESC, OrderLineMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     *Возвращает список с информацией о цене, дате заказа и продукте в заказе
     **/
    fun joinOrderLineWithProductWithOrder(): List<OrderLineWithOrderWithProduct> {
        return try {
            dbConnection.executeQuery(Script.SELECT_ORDERS_LINE_PRODUCT_INFO, OrderLineWithOrderWithProductMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }
}