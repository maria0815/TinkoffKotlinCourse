import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
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
    val resource = CommonResource()

    val elapsed = measureTime {
        repeat(numberOfThreads) {
            executorService.execute {
                var flag = true
                while (flag) {
                    synchronized(resource) {
                        if (resource.value < 1_000_000) {
                            resource.value++
                        } else {
                            flag = false
                        }
                    }
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

class CommonResource {
    var value = 0
}
