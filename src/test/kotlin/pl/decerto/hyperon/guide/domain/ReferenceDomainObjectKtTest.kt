package pl.decerto.hyperon.guide.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonEngine

/**
 * Domain configuration allows to create references to other domain objects of the same collection/type.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class ReferenceDomainObjectKtTest {
    @Value("\${hyperon.profile}")
    private lateinit var profile: String

    @Autowired
    private lateinit var engine: HyperonEngine

    /**
     * Reference should point to existing object within domain configuration. Object
     * obtained by reference differs from object accessed by direct path only by the `callPath`.
     */
    @Test
    fun `should reference same element as directly access child of collection`() {
        val bronzeByReferenceDO = engine.getDomain(profile, "/PLANS[LIAB]/OPTIONS[BRONZE]") // accessing BRONZE element via reference
        val bronzeDO = engine.getDomain(profile, "/PLANS[FULL]/OPTIONS[BRONZE]") // accessing BRONZE element normally
        assertNotNull(bronzeByReferenceDO)
        assertEquals(bronzeDO.id, bronzeByReferenceDO.id) // same domain object ids
        assertEquals(bronzeDO.code, bronzeByReferenceDO.code) // same codes
        assertEquals(bronzeDO.path, bronzeByReferenceDO.path) // same paths
        assertNotEquals(bronzeDO.callPath, bronzeByReferenceDO.callPath) // BUT CALLPATH'S ARE DIFFERENT
        assertEquals("/PLANS[FULL]/OPTIONS[BRONZE]", bronzeDO.callPath) // standard path to bronze
        assertEquals("/PLANS[LIAB]/OPTIONS[BRONZE]", bronzeByReferenceDO.callPath) // reference path to bronze
    }

    /**
     * 1. Get domain reference pointing to ERS
     * 2. Get real domain element ERS
     * 3. Compare their specific, especially `callPath`
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should reference same element as directly access child of collection`() {
        val normalPathToERS: String = TODO()
        val referencePathToERS: String = TODO()
        val code: String = TODO()
        val callPathFromDomainObjectToERS: String = TODO()
        val callPathFromReferenceToERS: String = TODO()
        assertEquals("ERS", code)
        assertEquals(referencePathToERS, normalPathToERS)
        assertNotEquals(callPathFromReferenceToERS, callPathFromDomainObjectToERS)
    }
}