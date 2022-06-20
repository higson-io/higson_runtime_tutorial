package pl.decerto.hyperon.guide.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonEngine
import pl.decerto.hyperon.runtime.model.HyperonDomainObject

/**
 * Here you can learn about retrieving simple domain object and some basic configuration information about it.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class SimpleDomainObjectKtTest {
    @Value("\${hyperon.profile}")
    private lateinit var profile: String

    @Autowired
    private lateinit var engine: HyperonEngine

    @Test
    fun `should get basic information about domain object configuration`() {
        val fullDO: HyperonDomainObject = engine.getDomain(profile, "/PLANS[FULL]")
        assertEquals("FULL", fullDO.code)
        assertEquals("/PLANS[FULL]", fullDO.path)
        assertEquals("Rating Plan for summer 2016", fullDO.name)
        assertEquals("/", fullDO.parent.callPath) // parent is root directly
        assertNull(fullDO.parent.path) // parent is root directly
        assertEquals(0, fullDO.attributes.size) // no attributes
        assertEquals(0, fullDO.dynamicAttributes.size) // no dynamic attributes
        assertEquals("RATING_PLAN", fullDO.typeCode)
        assertEquals(3, fullDO.getChildren("OPTIONS").size)
        assertEquals(9, fullDO.getChildren("COVERAGES").size)
        assertEquals(2, fullDO.getChildren("DISCOUNTS").size)
    }

    /**
     * <pre>
     * 1. Get Domain Object from profile = "DEMO", with proper path to `PLANS->FULL->COVERAGES->PD`
     * 2. Compare some basic information about PD domain object:
     * a) Get code
     * b) Get path to domain object
     * c) Get name of domain object
     * d) Get parent code of domain object, hint: first get parent
     * e) Get number of defined attributes
     * f) Get number of dynamic attributes
    </pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get basic information about domain object configuration`() {
        val code: String = TODO()
        val path: String = TODO()
        val name: String = TODO()
        val parentCode: String = TODO()
        val attributesSize: Int = TODO()
        val dynamicAttributesSize: Int = TODO()
        assertEquals("PD", code)
        assertEquals("/PLANS[FULL]/COVERAGES[PD]", path)
        assertEquals("Property Damage Liability", name)
        assertEquals("FULL", parentCode)
        assertEquals(6, attributesSize)
        assertEquals(0, dynamicAttributesSize)
    }
}