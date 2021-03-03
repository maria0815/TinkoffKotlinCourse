import java.util.LinkedList

/**
 * Структура данных, представляющая собой список элементов, организованных по принципу FIFO
 */
class Queue<T> {

    private val dataOfElements = LinkedList<T>()

    /**
     * Возвращает размер очереди
     */
    val size get() = dataOfElements.size

    /**
     * Добавляет [element] в конец очереди и возвращает добавленный элемент
     */
    fun enqueue(element: T): T {
        dataOfElements.add(element)
        return element
    }

    /**
     * Возвращает первый элемент очереди и удаляет его из очереди
     */
    fun dequeue(): T {
        if (dataOfElements.isEmpty()) throw NoSuchElementException("Queue is empty.")
        return dataOfElements.removeFirst()
    }

    override fun toString(): String {
        return dataOfElements.joinToString(prefix = "[", postfix = "]")
    }
}

/**
 * Возвращает очередь с заданными [elements]
 */
fun <T> queueOf(vararg elements: T): Queue<T> {
    val queue = Queue<T>()
    for (element in elements) {
        queue.enqueue(element)
    }
    return queue
}