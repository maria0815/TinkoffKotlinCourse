import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    val timeout = 10L
    val producer = randomNumbersProducer(timeout)
    repeat(10) {
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