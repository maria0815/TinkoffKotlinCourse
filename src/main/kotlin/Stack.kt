/**
 * Структура данных, представляющая собой список элементов, организованных по принципу LIFO
 */
class Stack<T> {

    private val dataOfElements = mutableListOf<T>()

    /**
     * Возвращает размер стека
     */
    val size get() = dataOfElements.size

    /**
     * Добавляет [element] в стек и возвращает добавленный элемент
     */
    fun push(element: T): T {
        dataOfElements.add(element)
        return element
    }

    /**
     * Возвращает верхний элемент и удаляет его из стека
     */
    fun pop(): T {
        if (dataOfElements.isEmpty()) throw NoSuchElementException("Stack is empty.")
        return dataOfElements.removeLast()
    }

    override fun toString(): String {
        return dataOfElements.joinToString(prefix = "[", postfix = "]")
    }
}

/**
 * Возвращает стек с заданными [elements]
 */
fun <T> stackOf(vararg elements: T): Stack<T> {
    val stack = Stack<T>()
    for (element in elements) {
        stack.push(element)
    }
    return stack
}