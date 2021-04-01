import kotlin.concurrent.thread

fun main() {
    MyThread().apply { start() }.join()

    Thread(MyRunnable()).apply {
        name = "CreatedFromRunnable"
        start()
    }.join()

    thread(name = "CreatedFromDSL") {
        println("Пример создания потока через класс DSL. Имя потока: ${Thread.currentThread().name}")
    }.join()

    thread(isDaemon = true,
        name = "DaemonThread") {
        println("Пример создания потока демона(IsDaemon=${Thread.currentThread().isDaemon}). Имя потока: ${Thread.currentThread().name}")
    }

    val highPriorityThread = thread(name = "ThreadWithHighPriority", priority = Thread.MAX_PRIORITY) {
        println("Пример создания потока c высоким приоритетом. Имя потока: ${Thread.currentThread().name}")
    }

    val lowPriorityThread = thread(name = "ThreadWithLowPriority", priority = Thread.MIN_PRIORITY) {
        println("Пример создания потока c низким приоритетом. Имя потока: ${Thread.currentThread().name}")
    }
    highPriorityThread.join()
    lowPriorityThread.join()
}

class MyRunnable : Runnable {
    override fun run() {
        println("Пример создания потока через класс Runnable. Имя потока: ${Thread.currentThread().name}")
    }
}

class MyThread : Thread() {
    init {
        name = "CreatedFromThreadClass"
    }

    override fun run() {
        println("Пример создания потока через класс Thread. Имя потока: ${currentThread().name}")
    }
}