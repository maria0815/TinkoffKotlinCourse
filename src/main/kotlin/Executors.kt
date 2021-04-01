import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@ExperimentalTime
fun main() {
    listOf(10, 20, 30).map {
        object {
            val numberOfThreads = it
            val timeElapsed = measureRunTime(it)
        }
    }.sortedByDescending { it.timeElapsed }
        .forEach { println("Пул из ${it.numberOfThreads} потоков отработал за ${it.timeElapsed} мс") }
}

@ExperimentalTime
fun measureRunTime(numberOfThreads: Int): Double {
    val executorService = Executors.newFixedThreadPool(numberOfThreads)
    val counter = AtomicInteger()

    val elapsed = measureTime {
        repeat(numberOfThreads) {
            executorService.execute {
                while (true) {
                    val currentValue = counter.get()
                    val nextValue = currentValue + 1
                    if (currentValue >= 1_000_000) {
                        return@execute
                    }
                    counter.compareAndSet(currentValue, nextValue)
                }
            }
        }
        executorService.shutdown()
        val terminated = executorService.awaitTermination(5, TimeUnit.SECONDS)
        if (!terminated) {
            throw InterruptedException("Пул потоков не успел завершиться.")
        }
    }
    return elapsed.inMilliseconds
}
