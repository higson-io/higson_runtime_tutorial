package pl.decerto.hyperon.guide.intro

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine

private const val PROFILE = "DEMO"
private const val ORDER = "ORDER"

/**
 * Whenever Hyperon Engine is calling for Domain Objects, the profile must be provided,
 * since domain definition/configuration is always associated with a particular profile.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class DomainKtTest {
    @Autowired
    private lateinit var engine: HyperonEngine

    @Test
    fun `should get domain_object`() {
        val bronzeDO = engine.getDomain(PROFILE, "/PLANS[FULL]/OPTIONS[BRONZE]")
        val orderNr = bronzeDO.getAttribute(ORDER).getInteger(HyperonContext())
        assertEquals(1, orderNr.toInt())
        assertEquals(1, bronzeDO.getAttrInteger(ORDER, HyperonContext()).toInt())
    }

    /**
     * <pre>
     * 1. Get Domain Object from profile = "DEMO", with proper path to "GOLD" option.
     * 2. Create context for attribute, using HyperonContext.
     * 3. Get "ORDER" attribute from retrieved Domain Object using created context.
     * 4. Compare results:
     * a) Get value using: "getAttribute" and assign it to orderNr case1
     * b) Get value using: "getAttrInteger" and assign it to orderNr_case2
    </pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get domain object`() {
        val orderNr_case1: Int = TODO()
        val orderNr_case2: Int = TODO()
        assertEquals(3, orderNr_case1)
        assertEquals(3, orderNr_case2)
    }
}