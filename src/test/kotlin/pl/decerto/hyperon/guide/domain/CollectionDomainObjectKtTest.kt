package pl.decerto.hyperon.guide.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonEngine

@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class CollectionDomainObjectKtTest {
    @Value("\${hyperon.profile}")
    private lateinit var profile: String

    @Autowired
    private lateinit var engine: HyperonEngine

    @Test
    fun `should directly access child of collection`() {
        val fullDO = engine.getDomain(profile, "/PLANS[FULL]")
        val missingAttribute = fullDO.getAttr("missing_attribute")
        assertNull(missingAttribute)
        val discountDE = fullDO.getChild("DISCOUNTS", "DE")
        val positionDE = discountDE.getAttribute("position")
        val discountGD = fullDO.getChild("DISCOUNTS", "GD")
        val positionGD = discountGD.getAttribute("position")
        assertEquals("/PLANS[FULL]/DISCOUNTS[DE]", discountDE.path)
        assertEquals(1, positionDE.getInteger(null))
        assertEquals("/PLANS[FULL]/DISCOUNTS[GD]", discountGD.path)
        assertEquals(2, positionGD.getInteger(null))
    }

    @Test
    fun `should iterate over discounts and match position attributes`() {
        val fullDO = engine.getDomain(profile, "/PLANS[FULL]")
        val missingAttribute = fullDO.getAttr("missing_attribute")
        assertNull(missingAttribute)
        val discounts = fullDO.getChildren("DISCOUNTS")
        assertEquals(2, discounts.size)
        discounts.forEach {
            if (it.code == "DE") {
                assertEquals("/PLANS[FULL]/DISCOUNTS[${it.code}]", it.path)
                val position = it.getAttribute("position")
                assertEquals(1, position.getInteger(null))
            }
            else if (it.code == "GD") {
                assertEquals("/PLANS[FULL]/DISCOUNTS[${it.code}]", it.path)
                val position = it.getAttribute("position")
                assertEquals(2, position.getInteger(null))
            }
        }
    }

    /**
     * 1. Get proper domain object using path `PLANS -> FULL -> OPTIONS -> GOLD`.
     * 2. Get attribute "ORDER".
     * 3. Compare result:
     * a) Get path to gold domain object
     * b) Get ORDER attribute name
     * c) Get value of ORDER attribute
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should find child of collection using direct access`() {
        val pathToGoldDO: String = TODO()
        val orderAttributeName: String = TODO()
        val orderAttributeValue: Int = TODO()
        assertEquals("/PLANS[FULL]/OPTIONS[GOLD]", pathToGoldDO)
        assertEquals("Display order", orderAttributeName)
        assertEquals(3, orderAttributeValue)
    }

    /**
     * 1. Get proper domain parent object `PLANS -> FULL`.
     * 2. Get child named "DISCOUNTS" - it will return list of domain objects.
     * 3. Iterate over list and aggregate by attribute "value". hint: might as well use streams
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should sum discount amounts`() {
        val pathToFullDO: String = TODO()
        val sumOfValue: Double = TODO()
        assertEquals("/PLANS[FULL]", pathToFullDO)
        assertEquals(35.0, sumOfValue)
    }
}