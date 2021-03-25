import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class StringExtensionsTest {
    @Test
    fun `should return true for palindrome`() {
        mockkStatic("StringExtensionsKt")
        every { "level".isPalindrome() }.returns(true)

        val palindrome = "level".isPalindrome()

        verify { "level".isPalindrome() }
        assertTrue(palindrome)
    }

    @Test
    fun `should return false for non-palindrome`() {
        mockkStatic("StringExtensionsKt")
        every { "Race fast".isPalindrome() }.returns(false)

        val palindrome = "Race fast".isPalindrome()

        verify { "Race fast".isPalindrome() }
        assertFalse(palindrome)
    }

    @Test
    fun `for an empty string should throw IllegalArgumentException`() {
        mockkStatic("StringExtensionsKt")
        every { "".isPalindrome() }
            .throws(IllegalArgumentException("Can't determine if an empty string is a palindrome!"))

        val exception = assertThrows<IllegalArgumentException> { "".isPalindrome() }

        verify { "".isPalindrome() }
        assertEquals("Can't determine if an empty string is a palindrome!", exception.message)
    }
}