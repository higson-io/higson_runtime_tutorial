package pl.decerto.hyperon.guide.context

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.guide.context.model.Option
import pl.decerto.hyperon.guide.context.model.Quote
import pl.decerto.hyperon.runtime.core.HyperonEngine
import java.math.BigDecimal

/**
 * In this example you will learn how to combine Hyperon Context (using adapters) with collections, using
 * [pl.decerto.hyperon.ext.adapter.CollectionAdapter]. For this let's examine [pl.decerto.hyperon.guide.context.adapter.QuoteAdapter] example.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class ContextWithCollectionAdaptersKtTest {
    @Value("\${hyperon.profile}")
    private lateinit var profile: String

    @Autowired
    private lateinit var engine: HyperonEngine

    /**
     * This test creates quote and multiple options assigned to quote, which is put then to context.
     * Then function "tutorial.options.allSeparatedCodes" is called - this function iterates over collection of options
     * (thanks to [pl.decerto.hyperon.ext.adapter.CollectionAdapter]) and return all option codes.
     */
    @Test
    fun `should return all option codes with adapter context`() {
        val quote = Quote("FULL").apply {
            addOption(Option("BRONZE"))
            addOption(Option("SILVER"))
            addOption(Option("GOLD"))
        }
        val context = MotorContext(quote)
        val allCodes = engine.call("tutorial.options.allSeparatedCodes", context) as String
        assertEquals("BRONZE,SILVER,GOLD", allCodes)
    }

    /**
     * 1. Use previously created `Coverage` class and apply it as `List<Coverage>` field to [Quote].
     * 2. Alter adapter [pl.decerto.hyperon.guide.context.adapter.QuoteAdapter] with new mapping for coverages -
     * use `CollectionAdapter`.
     * 3. Create new groovy function, that will iterate over 3 coverages within quote (context) and sum values from
     * "limit1", returning BigDecimal.
     * 4. Create context with values as presented below:
     * a) coverage1 - code: "BI", limit1: 1.43
     * b) coverage2 - code: "COLL", limit1: 2.21
     * c) coverage3 - code: "ERS", limit1: 4.55
     * 5. Compare result as BigDecimal
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should return sum limit 1 of three coverages with adapter context`() {
        val result: BigDecimal = TODO()
        assertEquals(BigDecimal("8.19"), result)
    }
}