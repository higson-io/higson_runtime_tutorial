package pl.decerto.hyperon.guide.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine
import pl.decerto.hyperon.runtime.model.HyperonDomainObject
import pl.decerto.hyperon.runtime.model.Type
import pl.decerto.hyperon.runtime.model.Type.*

/**
 * This tests helps to understand how attributes of domain object work. Attribute may contain not only
 * "raw" value, but can also have a function or parameter attached. Of course, if a function or a parameter
 * is attached, proper context (HyperonContext) must be provided.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class ComplexAttributeDomainObjectKtTest {
    @Value("\${hyperon.profile}")
    private lateinit var profile: String

    @Autowired
    private lateinit var engine: HyperonEngine

    /**
     * Example of fetching attribute with raw value.
     */
    @Test
    fun `should get raw attribute of domain object using full path`() {
        val biDO: HyperonDomainObject = engine.getDomain(profile, "/PLANS[FULL]/COVERAGES[BI]")
        assertTrue(biDO.isAttrSet("DESCRIPTION"))
        val descriptionAttr = biDO.getAttribute("DESCRIPTION")
        assertEquals("DESCRIPTION", descriptionAttr.code)
        assertEquals("Coverage description", descriptionAttr.name)
        val expected = "Bodily injury liability provides protection if you injure or kill someone while operating your car."
        assertEquals(expected, descriptionAttr.getString(HyperonContext()))
        assertEquals(LITERAL, descriptionAttr.rawType) // if value is directly written in attribute, then it will be marked as LITERAL
        assertEquals(expected, descriptionAttr.rawValue)
        assertEquals(biDO, descriptionAttr.domainObject)
    }

    /**
     * Example of attribute with parameter attached to a value.
     */
    @Test
    fun `should get parameter attribute of domain object using full_path`() {
        val biDO: HyperonDomainObject = engine.getDomain(profile, "/PLANS[FULL]/COVERAGES[BI]")
        assertTrue(biDO.isAttrSet("IS_AVAILABLE"))
        val isAvailableAttr = biDO.getAttribute("IS_AVAILABLE")
        val context = exampleContext()
        assertEquals("IS_AVAILABLE", isAvailableAttr.code)
        assertEquals("Whether this coverage is available for current option", isAvailableAttr.name)
        assertTrue(isAvailableAttr.getBoolean(context))
        assertEquals(PARAMETER, isAvailableAttr.rawType) // if value is directly written in attribute, then it will be marked as LITERAL
        assertEquals("demo.motor.coverage.availability", isAvailableAttr.rawValue)
        assertEquals(biDO, isAvailableAttr.domainObject)
    }

    /**
     * Example of attribute with function attached to a value.
     */
    @Test
    fun `should get function attribute of domain object using full path`() {
        val biDO: HyperonDomainObject = engine.getDomain(profile, "/PLANS[FULL]/COVERAGES[BI]")
        assertTrue(biDO.isAttrSet("PREMIUM"))
        val premiumAttr = biDO.getAttribute("PREMIUM")
        val context = exampleContext()
        assertEquals("PREMIUM", premiumAttr.code)
        assertEquals("Base premium for this coverage", premiumAttr.name)
        assertEquals(50.7, premiumAttr.getNumber(context))
        assertEquals(FUNCTION, premiumAttr.rawType) // if value is directly written in attribute, then it will be marked as LITERAL
        assertEquals("demo.motor.coverage.bi.calculatePremium", premiumAttr.rawValue)
        assertEquals(biDO, premiumAttr.domainObject)
    }

    /**
     * 1. Create proper hyperon context.
     * 2. Get proper domain object for `PLANS -> FULL -> COVERAGES -> PD`
     * 3. Compare results for attribute "DESCRIPTION":
     * a) Check if attribute exists
     * b) Get code of attribute
     * c) Get value of attribute
     * d) Get raw type of attribute's value
     * e) Get raw value
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get raw attribute of domain object using full path`() {
        val description = "DESCRIPTION"
        val isAttributeDescriptionSet: Boolean = TODO()
        val descriptionCode: String = TODO()
        val descriptionName: String = TODO()
        val descriptionValue: String = TODO()
        val rawType: Type = TODO()
        val rawValue: String = TODO()
        assertTrue(isAttributeDescriptionSet)
        assertEquals(description, descriptionCode)
        assertEquals("Coverage description", descriptionName)
        val expectedDescription = "Property damage liability protects you if your car damages someone else's property. " +
                "It also provides you with legal defense if another party files a lawsuit against you."
        assertEquals(expectedDescription, descriptionValue)
        assertEquals(LITERAL, rawType)
        assertEquals(expectedDescription, rawValue)
    }

    /**
     * 1. Create proper hyperon context.
     * 2. Get proper domain object for `PLANS -> FULL -> COVERAGES -> PD`
     * 3. Compare results for attribute "POSITION":
     * a) Check if attribute exists
     * b) Get code of attribute
     * c) Get value of attribute
     * d) Get raw type of attribute's value
     * e) Get raw value
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get parameter attribute of domain object using full path`() {
        val isAttributePositionSet: Boolean = TODO()
        val positionCode: String = TODO()
        val positionName: String = TODO()
        val positionValue: Int = TODO()
        val rawType: Type = TODO()
        val rawValue: String = TODO()
        assertTrue(isAttributePositionSet)
        assertEquals("POSITION", positionCode)
        assertEquals("Position on the screen or printout  (1, 2, ...)", positionName)
        assertEquals(2, positionValue)
        assertEquals(PARAMETER, rawType)
        assertEquals("demo.motor.coverage.position", rawValue)
    }

    /**
     * 1. Create proper hyperon context.
     * 2. Get proper domain object for `PLANS -> FULL -> COVERAGES -> UMPD`
     * 3. Compare results for attribute "PREMIUM":
     * a) Check if attribute exists
     * b) Get code of attribute
     * c) Get value of attribute
     * d) Get raw type of attribute's value
     * e) Get raw value
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get function attribute of domain object using full path`() {
        val isAttributePremiumSet: Boolean = TODO()
        val premiumCode: String = TODO()
        val premiumName: String = TODO()
        val premiumValue: Double = TODO()
        val rawType: Type = TODO()
        val rawValue = TODO()
        assertTrue(isAttributePremiumSet)
        assertEquals("PREMIUM", premiumCode)
        assertEquals("Base premium for this coverage", premiumName)
        assertEquals(10.11, premiumValue)
        assertEquals(FUNCTION, rawType)
        assertEquals("demo.motor.plan.full.calculatePremium", rawValue)
    }

    private fun exampleContext(): HyperonContext = HyperonContext(
        "coverage.code",
        "BI",
        "option.code",
        "BRONZE",
        "quote.driver.gender",
        "F",
        "quote.driver.age",
        20
    )
}
