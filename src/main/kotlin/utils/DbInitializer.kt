package utils

import script.Script

class DbInitializer(private val dbConnection: DbConnection) {

    /**
     *Настраивает работу с базой данных: удаляет старые таблицы, создает новые таблицы,
     * заполняет новые таблицы данными
     */
    fun setup() {
        dropTablesIfExists()
        createTables()
        insertData()
    }

    /**
     *Заканчивает работу с базой данных: удаляет существующие таблицы
     */
    fun tearDown() {
        dropTablesIfExists()
    }

    private fun createTables() {
        dbConnection.executeNonQuery(Script.Create.CLIENT)
        dbConnection.executeNonQuery(Script.Create.PRODUCT)
        dbConnection.executeNonQuery(Script.Create.ORDER)
        dbConnection.executeNonQuery(Script.Create.ORDER_lINE)
    }

    private fun insertData() {
        dbConnection.executeNonQuery(Script.Insert.CLIENT)
        dbConnection.executeNonQuery(Script.Insert.PRODUCT)
        dbConnection.executeNonQuery(Script.Insert.ORDER)
        dbConnection.executeNonQuery(Script.Insert.ORDER_LINE)
    }

    private fun dropTablesIfExists() {
        dbConnection.executeNonQuery(Script.Drop.ORDER_LINE)
        dbConnection.executeNonQuery(Script.Drop.ORDER)
        dbConnection.executeNonQuery(Script.Drop.PRODUCT)
        dbConnection.executeNonQuery(Script.Drop.CLIENT)
    }
}