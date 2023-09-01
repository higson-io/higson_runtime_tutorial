package pl.decerto.hyperon.guide.context

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.guide.context.SimpleCustomContextWithAdaptersKtTest.Gender.FEMALE
import pl.decerto.hyperon.guide.context.adapter.DriverAdapter
import pl.decerto.hyperon.guide.context.model.Driver
import pl.decerto.hyperon.guide.context.model.Quote
import pl.decerto.hyperon.runtime.core.HyperonEngine
import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZoneId.systemDefault
import java.util.*

/**
 * In this section you will learn a new way of creating Hyperon Context using adapters approach.
 * In Hyperon Runtime there are classes which help to work with this:
 *
 *  1. [pl.decerto.hyperon.ext.adapter.Adapter] - basic adapter, which is actually a Hyperon subcontext
 *  1. [pl.decerto.hyperon.ext.adapter.CollectionAdapter] - adapter for collection of adapters
 *  1. [pl.decerto.hyperon.ext.adapter.Mapping] - storage for mapping between context's subpaths and simple fields or nested
 * objects/adapters
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class SimpleCustomContextWithAdaptersKtTest {
    @Value("\${hyperon.profile}")
    private lateinit var profile: String

    @Autowired
    private lateinit var engine: HyperonEngine

    internal enum class Gender {
        FEMALE, MALE
    }

    @Test
    fun `should get value from parameter using context as adapter`() {
        val date = dateOfBirth()
        val driver = Driver().apply {
            firstName = "John"
            lastName = "Potter"
            gender = "M"
            dateOfBirth = date
        }
        val quote = Quote("FULL", driver)
        val context = MotorContext(quote)
        assertTrue(context["quote.driver"] is DriverAdapter)
        assertEquals("John", context.getString("quote.driver.firstname"))
        assertEquals("Potter", context.getString("quote.driver.lastname"))
        assertEquals("M", context.getString("quote.driver.gender"))
        assertEquals(date, context.getDate("quote.driver.dateofbirth"))
        assertEquals(24, context.getInteger("quote.driver.age").toInt())
        val entry = engine["demo.motor.coverage.pd.premium", context]
        val factor = entry.get<BigDecimal>("factor")
        assertEquals(BigDecimal("110.24"), factor)
    }

    /**
     * 1. Build objects of quote, driver and pass them to `MotorContext`.
     * 2. Get parameter: "tutorial.enum.example" and pass created context.
     * 3. Compare result as enum.
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get value from parameter using context as adapter`() {
        val firstName: String = TODO()
        val lastName: String = TODO()
        val gender: String = TODO()
        assertEquals("Marta", firstName)
        assertEquals("Borek", lastName)
        assertEquals("F", gender)
        val result: Gender = TODO()
        assertEquals(FEMALE, result)
    }

    /**
     * 1. Add extra Integer field - "licenceObtainedAtAge" to [Driver]. Also getter/setter.
     * 2. Provide proper mapping for subpath - "licenceObtainedAtAge" in [DriverAdapter].
     * 3. Build objects of quote, driver and pass them to `MotorContext`.
     * 4. Call function "demo.motor.util.drivingLicenceYears".
     * 5. Compare result as Integer.
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should call function using context as adapter`() {
        val drivingLicenceYears: Int = TODO()
        assertEquals(4, drivingLicenceYears)
    }

    /**
     * 1. Create new "Coverage" class.
     * a) add field: String code
     * b) add field: BigDecimal limit1
     * c) create getters/setters
     * 2. Add new coverage property to [Quote] with getter/setter.
     * 3. Create adapter class for Coverage.
     * a) create proper mapping for all Coverage fields
     * 4) Add new mapping for coverage adapter within [pl.decerto.hyperon.guide.context.adapter.QuoteAdapter].
     * 5) Create proper context with required values for function call >> "demo.motor.plan.full.calculatePremium".
     * 6) Call function with context.
     * 7) Compare result.
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should call function for coverages context ad adapter`() {
        val result: Double = TODO()
        assertEquals(0.12, result, 0.00001)
    }

    private fun dateOfBirth(): Date {
        val expectedDriverAge = 24L
        return Date.from(
            LocalDate.now()
                .minusYears(expectedDriverAge)
                .atStartOfDay()
                .atZone(systemDefault())
                .toInstant()
        )
    }
}