package pl.decerto.hyperon.guide.parameters

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine

/**
 * There is possibility for returning null value from matrix. For that to happen,
 * parameter must bo configured in studio with flag "Match required" to false.
 * When it is set up, Hyperon Engine will not throw exception on not matched
 * context and will return `null`.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class NullableValueInMatrixParameterKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine

    @Test
    fun `should get empty param value for missing code from parameter`() {
        val ctx = HyperonContext("quote.driver.gender", "A")
        val paramValue = hyperonEngine["tutorial.enum.example", ctx]
        assertEquals(0, paramValue.size())
        assertTrue(paramValue.isEmpty) // better check if size of matched rows is 0
        assertTrue(paramValue.isBlank) // can be used for checking empty rows and cells as well, not only whole paramValue
        assertNull(paramValue.string)
        assertNull(paramValue.get())
        assertNull(paramValue.get(String::class.java))
    }

    /**
     * <pre>
     * 1. Create context for parameter matrix, using HyperonContext.
     * 2. Get parameter called "tutorial.array.example".
     * 3. Compare results:
     * a) Check number of matched rows using size() on instance of ParamValue
     * b) Check if paramValue is isEmpty() and assign it to result1
     * c) Check if paramValue is isBlank() and assign it to result2
     * d) Check if paramValue getString() is null and assign it to result3
    </pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get array of string from parameter`() {
        val matchedRows: Int = TODO()
        val result1: Boolean = TODO()
        val result2: Boolean = TODO()
        val result3: String = TODO()
        assertEquals(0, matchedRows)
        assertTrue(result1)
        assertTrue(result2)
        assertNull(result3)
    }
}