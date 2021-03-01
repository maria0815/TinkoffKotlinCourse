/**
 * Структура данных, представляющая собой список элементов, организованных по принципу LIFO
 */
class Stack<T> {

    private val dataOfElements = mutableListOf<T>()

    /**
     * Добавляет элемент в стек
     * @param element - элемент, который будет добавлен в стек
     * @return возвращает добавленный в стек элемент
     */
    fun push(element: T): T {
        dataOfElements.add(element)
        return element
    }

    /**
     * Возвращает верхний элемент и удаляет его из стека
     * @return возвращает элемент с верхушки стека
     */
    fun pop(): T {
        if (dataOfElements.isEmpty())
            throw NoSuchElementException("Stack is empty.")

        return dataOfElements.removeLast()
    }

    override fun toString(): String {
        return dataOfElements.joinToString(prefix = "[", postfix = "]")
    }
}

/**
 * Возвращает стек
 * @param elements - элементы, которые будут добавлены в стек при создании
 * @return возвращает стек с заданными элементами
 */
fun <T> stackOf(vararg elements: T): Stack<T> {
    val stack = Stack<T>()
    for (element in elements) {
        stack.push(element)
    }
    return stack
}