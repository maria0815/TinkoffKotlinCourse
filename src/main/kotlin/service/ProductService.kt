package service

import entity.*
import utils.DbConnection
import script.Script
import java.sql.SQLException

class ProductService(private val dbConnection: DbConnection) {

    /**
     *Возвращает продукт с идентификатором равным [id]
     */
    fun findProductsWithIdIs2(id: Int): Product? {
        return try {
            dbConnection.executeQueryForObject(Script.SELECT_PRODUCT_WITH_ID, ProductMapper(), id)
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            null
        }
    }

    /**
     *Возвращает список продуктов, у которых идентификатор больше, чем [id]
     */
    fun findProductsByIdGrater(id: Int): List<Product> {
        return try {
            dbConnection.executeQuery(Script.SELECT_PRODUCTS_WHERE_ID_GRATER1, ProductMapper(), id)
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     *Возвращает список количества продуктов по каждому производителю
     */
    fun groupProductsByManufacturer(): List<ProductGroupedByManufacturer> {
        return try {
            dbConnection
                .executeQuery(Script.GROUP_PRODUCTS_BY_MANUFACTURER, ProductGroupedByManufacturerMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }

    /**
     *Возвращает список продуктов, отсортированных по убыванию id
     */
    fun sortProductsInDescendingOfId(): List<Product> {
        return try {
            dbConnection.executeQuery(Script.SORT_PRODUCTS_BY_ID_DESC, ProductMapper())
        } catch (exception: SQLException) {
            println("Ошибка выполнения запроса. ${exception.message}")
            listOf()
        }
    }
}