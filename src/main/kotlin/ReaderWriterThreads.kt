import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

fun main() {
    val countDownLatch = CountDownLatch(3)
    val data = Data()

    val writerThread = thread(name = "Writer") {
        countDownLatch.await()
        repeat(6) {
            println(Thread.currentThread().name + " increment ${++data.variable}")
            Thread.sleep(200)
        }
    }
    repeat(3) {
        thread(name = "Reader $it") {
            countDownLatch.countDown()
            var oldValue = data.variable
            while (data.variable <= 6) {
                if (data.variable != oldValue) {
                    println(Thread.currentThread().name + " read new variable ${data.variable}")
                    oldValue = data.variable
                }
            }
        }
    }

    writerThread.join()
}

class Data {
    @Volatile
    var variable = 0
}