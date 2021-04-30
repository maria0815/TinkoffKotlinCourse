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

//
//Нужно написать сервис, который будет включать в себя две
// взаимосвязанные сущности. Http сервер должен предоставлять
// endpoint'ы, которые позволяют получать, создавать, редактировать,
// удалять, а также устанавливать взаимосвязь между сущностями.
// Также необходимо написать миграции для создания таблиц под эти
// сущности.
//
//Пример сервиса:
//Сервис предоставляет возможность создания и редактирования
// двух сущностей - группа и студент.
// Также есть возможность добавить студента в группу,
// при этом студент перемещается из группы в группу,
// если он уже был прикреплён к какой либо, и есть возможность
// посмотреть всех студентов конкретной группы.

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
        orderModule()
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