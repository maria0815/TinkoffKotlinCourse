import clients.clientModule
import com.typesafe.config.ConfigFactory
import configurations.AppConfig
import configurations.DatabaseConfig
import io.github.config4k.extract
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import orders.orderModule
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import plugins.configureSerialization

fun main() {
    val config = ConfigFactory.load().extract<AppConfig>()

    migrate(config.database)
    val database = Database.connect(
        url = config.database.url,
        user = config.database.user,
        password = config.database.password
    )

    val engine = embeddedServer(Netty, port = config.http.port, host = config.http.host) {
        clientModule(database)
        orderModule(database)
        configureSerialization()
    }
    engine.start(wait = true)
}

fun migrate(database: DatabaseConfig) {
    Flyway
        .configure()
        .dataSource(database.url, database.user, database.password)
        .load()
        .migrate()
}