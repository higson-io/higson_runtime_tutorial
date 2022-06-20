package pl.decerto.hyperon.guide.functions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine

/**
 * There is also a possibility of passing complex object that are not supported
 * by Hyperon. This test will show how to call a function and pass any object.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class ExternalCallAsParameterFunctionKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine

    @Test
    fun `should call function with custom class`() {
        val user = User("Jan", "Kowalski")
        val result = hyperonEngine.call("tutorial.external.class.function", HyperonContext(), user) as String
        assertEquals("Function call for user:Jan Kowalski", result)
    }

    /**
     * 1. Extend class user with field "age" as Integer and expose it with getAge()
     * 2. Call function 'tutorial.external.class.function2'
     * 3. Compare result as String
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should call function with custom class`() {
        val result: String = TODO()
        assertEquals("Function call for user:Jan Kowalski 15", result)
    }
}