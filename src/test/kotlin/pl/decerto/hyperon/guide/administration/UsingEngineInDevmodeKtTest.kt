package pl.decerto.hyperon.guide.administration

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.CustomHyperonIntegrationKtConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine

/**
 * Before running the tests you should properly set up the  Hyperon Engine in developer mode.
 * Go to [CustomHyperonIntegrationKtConfiguration] and fill in missing method code using guidance from comments.
 * You can review existing code from [pl.decerto.hyperon.guide.HyperonIntegrationConfiguration].
 *
 * Using Hyperon Engine with developer mode "ON" is transparent for developer. API remains the same, but developer using
 * runtime can access objects: parameter, function or domain, which are not published but remain in user's session.
 */
@Disabled("Remove this annotation when ready to run tests")
@SpringBootTest(classes = [CustomHyperonIntegrationKtConfiguration::class])
internal class UsingEngineInDevmodeKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine

    /**
     * 1. Find parameter in Hyperon Studio, which is not yet published and get it's full code.
     * 2. Get column "test"(first column) as String.
     * 3. Compare result.
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get value from created parameter but not yet published`() {
        val rowCount: Int = TODO()
        val parameterValue: String = TODO()
        assertEquals(1, rowCount)
        assertEquals("abc", parameterValue)
    }

    /**
     * 1. Find domain object in Hyperon Studio, which is not yet published and get its path.
     * 2. Get attribute "value" as int.
     * 3. Compare result.
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get attribute value from created domain object but not yet published`() {
        val result: Int = TODO()
        assertEquals(2, result)
    }

    /**
     * 1. Find function in Hyperon Studio, which is not yet published and get is's full code.
     * 2. Call function and cast result to String.
     * 3. Compare result.
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should call function which not yet published`() {
        val result: String = TODO()
        assertEquals("abc", result)
    }
}