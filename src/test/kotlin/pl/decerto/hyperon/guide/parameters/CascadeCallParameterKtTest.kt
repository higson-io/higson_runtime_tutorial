package pl.decerto.hyperon.guide.parameters

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.smartparam.engine.core.output.ParamValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine
import java.math.BigDecimal

/**
 * This test will present how the value returned by function can be used
 * to select parameter row by matching the value against "IN" column of the parameter.
 * This allows to calculate INPUT for parameter based on
 * complex logic, not only retrieving simple value from context.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class CascadeCallParameterKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine
    @Test
    fun `cascade call to function should return value from parameter`() {
        val context = HyperonContext()
        context["quote.driver.age"] = 35
        context["quote.driver.licenceObtainedAtAge"] = 25
        val paramValue = hyperonEngine["demo.motor.discount.drivingExperience", context]
        assertTrue(paramValue.get<Boolean>("available"))
        assertEquals(BigDecimal(20), paramValue.get<BigDecimal>("amountValue"))
    }

    /**
     * <pre>
     * 1. Create context for parameter matrix, using HyperonContext.
     * 2. Get parameter called "demo.motor.discount.drivingExperience".
     * 3. Compare results for columns: "available" and "amountValue".
     * hint: Verify what function defined in INPUT for parameter source value.
    </pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo cascade call to function should return value from parameter`() {
        val available: Boolean = TODO()
        val amountValue: BigDecimal = TODO()
        assertTrue(available)
        assertEquals(BigDecimal(30), amountValue)
    }
}