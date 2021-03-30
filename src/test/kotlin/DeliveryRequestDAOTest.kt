import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class DeliveryRequestDAOTest {

    @ParameterizedTest
    @CsvSource("1", "2", "3", "4")
    fun `method 'getDeliveryRequestById' should return a delivery request for id less than 5`(deliveryRequestId: Int) {
        val dao = mockk<DeliveryRequestDAO> {
            every { getDeliveryRequestById(id = and(more(0), less(5))) } returns mockk {
                every { id } returns 4
                every { address } returns "Spb"
            }
        }

        val result = dao.getDeliveryRequestById(deliveryRequestId)

        verify { dao.getDeliveryRequestById(deliveryRequestId) }
        assertNotNull(result)
        assertEquals(4, result.id)
        assertEquals("Spb", result.address)
    }

    @ParameterizedTest
    @CsvSource("0", "5")
    fun `method 'getDeliveryRequestById' should return null for id greater than 4`(deliveryRequestId: Int) {
        val dao = mockk<DeliveryRequestDAO>()

        every { dao.getDeliveryRequestById(id = or(less(1), more(4))) } returns null

        val result = dao.getDeliveryRequestById(deliveryRequestId)

        verify { dao.getDeliveryRequestById(deliveryRequestId) }
        assertNull(result)
    }

    @Test
    fun `'getAllDeliveryRequests' should return all delivery requests`() {
        val dao = mockk<DeliveryRequestDAO> {
            every { getAllDeliveryRequests() } returns listOf(
                mockk {
                    every { id } returns 1
                    every { address } returns "Spb"
                },
                mockk {
                    every { id } returns 2
                    every { address } returns "Msk"
                }
            )
        }

        val result = dao.getAllDeliveryRequests()

        verify { dao.getAllDeliveryRequests() }
        assertAll("Number of delivery requests should be 2",
            { assertEquals(1, result[0].id) },
            { assertEquals("Spb", result[0].address) },
            { assertEquals(2, result[1].id) },
            { assertEquals("Msk", result[1].address) }
        )
    }
}