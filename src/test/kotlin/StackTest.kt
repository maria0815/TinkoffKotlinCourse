import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StackTest {

    @Test
    fun `the last item on the stack must equal the number added to the stack`() {
        val stack = stackOf<Int>()
        assertEquals(0, stack.size)

        assertEquals(5, stack.push(5))
        assertEquals(2, stack.push(2))
        assertEquals(3, stack.push(3))
        assertEquals(4, stack.push(4))

        assertEquals("[5, 2, 3, 4]", stack.toString())
        assertEquals(4, stack.size)
    }

    @Test
    fun `the item removed from the stack is equal to the number that was at the top of the stack`() {
        val stack = stackOf(1, 3, 8)
        assertEquals(3, stack.size)
        assertEquals("[1, 3, 8]", stack.toString())

        assertEquals(8, stack.pop())
        assertEquals(3, stack.pop())
        assertEquals(1, stack.pop())

        assertEquals("[]", stack.toString())
        assertEquals(0, stack.size)
    }

    @Test
    fun `removing an item from an empty stack will throw an exception`() {
        val stack = stackOf<Int>()
        assertEquals(0, stack.size)

        assertThrows<NoSuchElementException> {
            stack.pop()
        }
    }

    @Test
    fun `creating a stack using the default constructor`() {
        val stack = Stack<Double>()
        assertEquals(0, stack.size)

        assertEquals(5.0, stack.push(5.0))
        assertEquals(3.0, stack.push(3.0))

        assertEquals("[5.0, 3.0]", stack.toString())
        assertEquals(2, stack.size)

        assertEquals(3.0, stack.pop())
        assertEquals("[5.0]", stack.toString())
        assertEquals(1, stack.size)
    }
}