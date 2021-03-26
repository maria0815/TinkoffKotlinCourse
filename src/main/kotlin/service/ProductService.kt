package service

import entity.Product
import entity.ProductGroupedByManufacturer
import entity.ProductGroupedByManufacturerMapper
import entity.ProductMapper
import utils.DbConnection
import script.Script

class ProductService(private val dbConnection: DbConnection) {

    /**
     *Возвращает продукт с идентификатором равным [id]
     */
    fun findProductsWithIdIs2(id: Int): Product? {
        return runCatching {
            dbConnection.executeQueryForObject(Script.SELECT_PRODUCT_WITH_ID, ProductMapper(), id)
        }.onFailure { println("Ошибка выполнения запроса. ${it.message}") }.getOrNull()
    }

    /**
     *Возвращает список продуктов, у которых идентификатор больше, чем [id]
     */
    fun findProductsByIdGrater(id: Int): List<Product> {
        return runCatching {
            dbConnection.executeQuery(Script.SELECT_PRODUCTS_WHERE_ID_GRATER, ProductMapper(), id)
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     *Возвращает список количества продуктов по каждому производителю
     */
    fun groupProductsByManufacturer(): List<ProductGroupedByManufacturer> {
        return runCatching {
            dbConnection
                .executeQuery(Script.GROUP_PRODUCTS_BY_MANUFACTURER, ProductGroupedByManufacturerMapper())
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }

    /**
     *Возвращает список продуктов, отсортированных по убыванию id
     */
    fun sortProductsInDescendingOfId(): List<Product> {
        return runCatching {
            dbConnection.executeQuery(Script.SORT_PRODUCTS_BY_ID_DESC, ProductMapper())
        }.onFailure {
            println("Ошибка выполнения запроса. ${it.message}")
        }.getOrDefault(emptyList())
    }
}