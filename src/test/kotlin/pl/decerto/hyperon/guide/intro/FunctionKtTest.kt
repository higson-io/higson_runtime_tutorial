package pl.decerto.hyperon.guide.intro

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.smartparam.engine.core.context.DefaultContext
import org.smartparam.engine.core.context.ParamContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine
import java.util.*

@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class FunctionKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine

    @Test
    fun `should call simple function and get value`() {
        val result = hyperonEngine.call("demo.motor.util.drivingLicenceYears", buildFunctionContext {
            with("quote.driver.age", 25)
            with("quote.driver.licenceObtainedAtAge", 19)
        })
        val drivingLicenceYears = result as Int
        assertEquals(6, drivingLicenceYears)
    }

    /**
     * 1. Create proper context for function.
     * 2. Call function named "demo.insurance.calcpremium" with created context.
     * 3. Cast it to double and compare result.
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should call simple function and get value`() {
        val calculatedPremium: Double = TODO()
        assertEquals(6.0, calculatedPremium)
    }

    private fun <CTX : DefaultContext> buildFunctionContext(init: HyperonContext.() -> CTX): CTX =
        init(HyperonContext())
}

