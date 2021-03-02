import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import kotlin.NoSuchElementException

/**
 * Структура данных, представляющая собой список элементов, организованных по принципу FIFO
 */
class Queue<T> {

    private val dataOfElements = LinkedList<T>()

    /**
     * Добавляет элемент в конец очереди
     * @param element - элемент, который будет добавлен в очередь
     * @return возвращает добавленный в очередь элемент
     */
    fun enqueue(element: T): T {
        dataOfElements.add(element)
        return element
    }

    /**
     * Возвращает первый элемент очереди и удаляет его из очереди
     * @return возвращает первый элемент очереди
     */
    fun dequeue(): T {
        if (dataOfElements.isEmpty())
            throw NoSuchElementException("Queue is empty.")
        return dataOfElements.removeFirst()
    }

    override fun toString(): String {
        return dataOfElements.joinToString(prefix = "[", postfix = "]")
    }
}

/**
 * Возвращает очередь
 * @param elements - элементы, которые будут добавлены в очередь при создании
 * @return возвращает очередь с заданными элементами
 */
fun <T> queueOf(vararg elements: T): Queue<T> {
    val queue = Queue<T>()
    for (element in elements) {
        queue.enqueue(element)
    }
    return queue
}