package entity

import java.sql.ResultSet

interface Mapper<T> {
    fun writeResultSetToClass(resultSet: ResultSet): T
}