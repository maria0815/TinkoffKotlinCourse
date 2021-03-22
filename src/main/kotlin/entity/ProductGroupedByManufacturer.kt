package entity

import java.sql.ResultSet

data class ProductGroupedByManufacturer(
    val manufacturer: String,
    val productCount: Int,
)

class ProductGroupedByManufacturerMapper : Mapper<ProductGroupedByManufacturer> {
    override fun writeResultSetToClass(resultSet: ResultSet): ProductGroupedByManufacturer {
        val manufacturer = resultSet.getString("manufacturer")
        val productCount = resultSet.getInt("product_count")

        return ProductGroupedByManufacturer(manufacturer, productCount)
    }
}