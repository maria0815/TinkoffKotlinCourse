import Constants.NUMBER_OF_RECEIVED_MESSAGES
import Constants.TIMEOUT_BETWEEN_MESSAGES
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val producer = randomNumbersProducer(TIMEOUT_BETWEEN_MESSAGES)
    repeat(NUMBER_OF_RECEIVED_MESSAGES) {
        println(producer.receive())
    }
    producer.cancel()
}

@ExperimentalCoroutinesApi
suspend fun CoroutineScope.randomNumbersProducer(timeout: Long) = produce {
    while (true) {
        send(Random.nextInt(0, 100))
        delay(timeout)
    }
}

object Constants {
    const val NUMBER_OF_RECEIVED_MESSAGES = 10
    const val TIMEOUT_BETWEEN_MESSAGES = 10L
}