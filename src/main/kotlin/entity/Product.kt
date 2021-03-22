package entity

import java.sql.ResultSet

data class Product(
    val id: Int,
    val name: String,
    val manufacturer: String,
    val color: String,
)

class ProductMapper : Mapper<Product> {
    override fun writeResultSetToClass(resultSet: ResultSet): Product {
        val id = resultSet.getInt("id")
        val name = resultSet.getString("name")
        val manufacturer = resultSet.getString("manufacturer")
        val color = resultSet.getString("color")

        return Product(id, name, manufacturer, color)
    }
}