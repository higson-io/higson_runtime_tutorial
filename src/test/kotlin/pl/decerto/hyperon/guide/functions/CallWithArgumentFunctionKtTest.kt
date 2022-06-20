package pl.decerto.hyperon.guide.functions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine
import java.math.BigDecimal

/**
 * Hyperon engine allows to call functions with parameters in two ways:
 *
 *  * all required values are passed within context - `hyperonEngine.call([function code], [context]) `
 *  * context is not enough or too big to use, then there is possibility to pass arguments (from 0 to any number)
 *  directly to function call - `hyperonEngine.call([function code], [context], [arguments]....)
` *
 *
 * **WARNING**<br></br>
 * If function is defined with arguments but is called without any, then the values of arguments
 * passed to the function are null.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class CallWithArgumentFunctionKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine

    @Test
    fun `should call function with argument`() {
        val ctx = HyperonContext()
            .with("coverage.limit1", 3.4)
            .with("coverage.limit2", 5.1)

        val expectedResultForTrue = BigDecimal("8.50")
        val canIncludeLimit2 = true
        val result = hyperonEngine.call("tutorial.simple.function.with.argument", ctx, canIncludeLimit2) as BigDecimal
        assertEquals(expectedResultForTrue, result)
    }

    @Test
    fun `should call function with null argument`() {
        val ctx =  HyperonContext()
            .with("coverage.limit1", 3.4)
            .with("coverage.limit2", 5.1)

        val expectedResultForFalse = BigDecimal("3.40")
        val result = hyperonEngine.call("tutorial.simple.function.with.argument", ctx) as BigDecimal

        // The programming language for Hyperon functions is Groovy.
        // In Groovy null has a boolean value of false
        assertEquals(expectedResultForFalse, result)
    }

    /**
     * 1. Create proper context for function.
     * 2. Call function named "tutorial.simple.function.with.argument" with created context and explicitly
     * pass one boolean argument.
     * 3. Cast it to BigDecimal and compare result.
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should call function with argument`() {
        val expectedResultForFalse = BigDecimal("3.40")
        val result: BigDecimal =  TODO()
        assertEquals(expectedResultForFalse, result)
    }

    /**
     * 1. Create proper context for function "tutorial.simple.function.with.argument2" - verify body of this function in Hyperon Studio.
     * 2. Call function named "tutorial.simple.function.with.argument2" with created context.
     * 3. Cast it to String and compare result.
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should call function with null argument`() {
        val result: String = TODO()
        assertEquals("Called function with: TESTaaaanull", result)
    }
}