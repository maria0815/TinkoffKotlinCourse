import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException


internal class StudentUtilsTest {
    @Test
    fun `student with a high average score has an honors degree`() {
        assertTrue(StudentUtils.isDiplomaWithHonours(5.0))
    }

    @Test
    fun `student with a low average score doesn't have an honors degree`() {
        assertFalse(StudentUtils.isDiplomaWithHonours(4.7))
    }

    @Test
    fun `incorrect average score should throw IllegalArgumentException`() {
        val exception = assertThrows<IllegalArgumentException> { StudentUtils.isDiplomaWithHonours(6.0) }
        assertEquals("Incorrect value of the average score!", exception.message)
    }

    @Test
    fun `for the number of points equal to 95, the mark must be Excellent!`() {
        assertEquals("Excellent!", StudentUtils.returnsScoreByNumberOfPointsForTask(95))
    }

    @Test
    fun `for the number of points equal to 77, the mark must be Good!`() {
        assertEquals("Good!", StudentUtils.returnsScoreByNumberOfPointsForTask(77))
    }

    @Test
    fun `for the number of points equal to 63, the mark must be Fair!`() {
        assertEquals("Fair!", StudentUtils.returnsScoreByNumberOfPointsForTask(63))
    }

    @Test
    fun `for the number of points equal to 59, the mark must be Unsatisfactory!`() {
        assertEquals("Unsatisfactory!", StudentUtils.returnsScoreByNumberOfPointsForTask(59))
    }

    @Test
    fun `incorrect number of points should throw IllegalArgumentException`() {
        val exception = assertThrows<IllegalArgumentException> {
            StudentUtils.returnsScoreByNumberOfPointsForTask(101)
        }
        assertEquals("Incorrect number of points!", exception.message)
    }
}