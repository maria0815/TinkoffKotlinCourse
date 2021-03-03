import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class QueueTest {

    @Test
    fun `the last item in the queue must equal the number added to the queue`() {
        val queue = queueOf<Int>()
        assertEquals(0, queue.size)

        assertEquals(5, queue.enqueue(5))
        assertEquals(6, queue.enqueue(6))
        assertEquals(8, queue.enqueue(8))
        assertEquals(10, queue.enqueue(10))

        assertEquals("[5, 6, 8, 10]", queue.toString())
        assertEquals(4, queue.size)
    }

    @Test
    fun `the item removed from the queue is equal to the number that was first in the queue`() {
        val queue = queueOf(1, 4, 0, 3, 11)
        assertEquals(5, queue.size)

        assertEquals(1, queue.dequeue())
        assertEquals(4, queue.dequeue())
        assertEquals(0, queue.dequeue())
        assertEquals(3, queue.dequeue())

        assertEquals("[11]", queue.toString())
        assertEquals(1, queue.size)
    }

    @Test
    fun `removing an item from an empty queue will throw an exception`() {
        val queue = queueOf<Int>()
        assertEquals(0, queue.size)

        assertThrows<NoSuchElementException> {
            queue.dequeue()
        }
    }

    @Test
    fun `creating a queue using the default constructor`() {
        val queue = Queue<Double>()
        assertEquals(0, queue.size)

        assertEquals(9.0, queue.enqueue(9.0))
        assertEquals(2.0, queue.enqueue(2.0))

        assertEquals("[9.0, 2.0]", queue.toString())
        assertEquals(2, queue.size)

        assertEquals(9.0, queue.dequeue())
        assertEquals("[2.0]", queue.toString())
        assertEquals(1, queue.size)
    }

}