package configurations

data class AppConfig(
    val http: HttpConfig,
    val database: DatabaseConfig
)

data class HttpConfig(val port: Int, val host: String)

data class DatabaseConfig(val url: String, val user: String, val password: String)