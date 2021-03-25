package service

import utils.DbConnection
import entity.OrderLineWithOrderWithProduct
import entity.OrderLineWithOrderWithProductMapper
import entity.OrderLine
import entity.OrderLineGroupedByProductId
import entity.OrderLineGroupedByProductIdMapper
import entity.OrderLineMapper
import script.Script

class OrderLineService(private val dbConnection: DbConnection) {
    /**
     * Возвращает строку заказа с [id], переданным на вход
     **/
    fun findOrderLineById(id: Int): OrderLine? {
        return runCatching {
            dbConnection.executeQueryForObject(Script.SELECT_ORDER_LINE_WHERE_ID, OrderLineMapper(), id)
        }.onFailure { println("Ошибка выполнения запроса. ${it.message}") }.getOrNull()
    }

    /**
     * Возвращает список строк заказов, у которых цена больше, чем [price]
     **/
    fun findOrderLineWithPriceGraterThen(price: Int): List<OrderLine> {
        return runCatching {
            dbConnection.executeQuery(Script.SELECT_ORDERS_LINE_WHERE_PRICE_GRATER, OrderLineMapper(), price)
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     * Возвращает список строк заказов, в которых заказано больше, чем [productCount] единиц
     **/
    fun findOrderLinesWithProductCountGraterThen(productCount: Int): List<OrderLineGroupedByProductId> {
        return runCatching {
            dbConnection.executeQuery(Script.GROUP_ORDERS_LINE_BY_PRODUCT_ID,
                OrderLineGroupedByProductIdMapper(),
                productCount)
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     * Возвращает список строк заказов, отсортированных по убыванию скидки
     **/
    fun sortOrderLinesInDescendingOfDiscount(): List<OrderLine> {
        return runCatching {
            dbConnection.executeQuery(Script.SORT_ORDERS_LINE_BY_DISCOUNT_DESC, OrderLineMapper())
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     *Возвращает список с информацией о цене, дате заказа и продукте в заказе
     **/
    fun joinOrderLineWithProductWithOrder(): List<OrderLineWithOrderWithProduct> {
        return runCatching {
            dbConnection.executeQuery(Script.SELECT_ORDERS_LINE_PRODUCT_INFO, OrderLineWithOrderWithProductMapper())
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }
}