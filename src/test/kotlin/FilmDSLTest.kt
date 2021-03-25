import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FilmDSLTest {

    @Test
    fun `number of reviews with a rating of 10 equals 1`() {
        val film = mockk<Film> {
            every { name } returns "Hachiko"
            every { reviews } returns mutableListOf(
                mockk {
                    every { rating } returns 8
                    every { comment } returns "Great!"
                },
                mockk {
                    every { rating } returns 10
                    every { comment } returns "I cried:("
                }
            )
            every { numberOfReviews(10) } returns 1
        }

        val result = film.numberOfReviews(10)

        verify { film.numberOfReviews(10) }
        assertAll("Film should store 2 mocked reviews",
            { assertEquals(1, result) },
            { assertEquals("Hachiko", film.name) },
            { assertEquals(2, film.reviews.size) },
            { assertEquals(8, film.reviews[0].rating) },
            { assertEquals("Great!", film.reviews[0].comment) },
            { assertEquals(10, film.reviews[1].rating) },
            { assertEquals("I cried:(", film.reviews[1].comment) }
        )
    }
}