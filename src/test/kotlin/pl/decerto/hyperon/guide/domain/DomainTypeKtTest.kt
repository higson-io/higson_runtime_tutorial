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
import pl.decerto.hyperon.runtime.model.HyperonDomainObjectType

/**
 * The following test illustrates how to obtain information about the type of domain object.
 *
 * The relation between domain types and domain objects is similar to the relation between
 * classes and objects in Java. In Hyperon Studio domain types are created and modified on
 * 'Domain definition' screen, and domain objects on screen 'Domain configuration'
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class DomainTypeKtTest {
    @Value("\${hyperon.profile}")
    private lateinit var profile: String

    @Autowired
    private lateinit var engine: HyperonEngine

    @Test
    fun `should have access to basic information about type of element`() {
        val collDO: HyperonDomainObject = engine.getDomain(profile, "/PLANS[FULL]/COVERAGES[COLL]")
        assertEquals("COVERAGE", collDO.typeCode)
        assertEquals("Risk Coverage", collDO.typeName)
        val typeCollection: HyperonDomainObjectType = collDO.typeCollection // TODO why we call this?
        assertEquals("COVERAGES", typeCollection.code)
        assertEquals(6, typeCollection.attributeDefinitions.size)
    }

    /**
     * 1. Get domain element: `PLANS->FULL->OPTIONS->SILVER`
     * 2. Compare some basic information about type of element:
     * a) Get type code
     * b) Get type name
     * c) Get type collection code
     * d) Get number of attributes
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should have access to basic information about type of element`() {
        val typeCode: String = TODO()
        val typeName: String = TODO()
        val typeCollectionCode: String = TODO()
        val numberOfAttributes: Int = TODO()
        assertEquals("OPTION", typeCode)
        assertEquals("Rating Option (Variation)", typeName)
        assertEquals("OPTIONS", typeCollectionCode)
        assertEquals(1, numberOfAttributes)
    }
}