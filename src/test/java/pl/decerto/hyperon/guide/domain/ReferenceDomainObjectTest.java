package pl.decerto.hyperon.guide.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.model.HyperonDomainObject;

/**
 * Domain configuration allows to create references to other domain objects of the same collection/type.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class ReferenceDomainObjectTest {

	@Value("${hyperon.profile}")
	private String profile;

	@Autowired
	private HyperonEngine engine;

	/**
	 * Reference should point to existing object within domain configuration. Object
	 * obtained by reference differs from object accessed by direct path only by the {@code callPath}.
	 */
	@Test
	void shouldReferenceSameElementAsDirectlyAccessChildOfCollection() {
		HyperonDomainObject bronzeByReferenceDO = engine.getDomain(profile, "/PLANS[LIAB]/OPTIONS[BRONZE]");  // accessing BRONZE element via reference
		HyperonDomainObject bronzeDO = engine.getDomain(profile, "/PLANS[FULL]/OPTIONS[BRONZE]");  // accessing BRONZE element normally

		assertNotNull(bronzeByReferenceDO);
		assertEquals(bronzeDO.getId(), bronzeByReferenceDO.getId()); // same domain object ids
		assertEquals(bronzeDO.getCode(), bronzeByReferenceDO.getCode()); // same paths
		assertEquals(bronzeDO.getPath(), bronzeByReferenceDO.getPath()); // same paths
		assertNotEquals(bronzeDO.getCallPath(), bronzeByReferenceDO.getCallPath()); // BUT CALLPATH'S ARE DIFFERENT
		assertEquals("/PLANS[FULL]/OPTIONS[BRONZE]", bronzeDO.getCallPath()); // standard path to bronze
		assertEquals("/PLANS[LIAB]/OPTIONS[BRONZE]", bronzeByReferenceDO.getCallPath()); // reference path to bronze
	}

	/**
	 * 1. Get domain reference pointing to ERS
	 * 2. Get real domain element ERS
	 * 3. Compare their specific, especially {@code callPath}
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldReferenceSameElementAsDirectlyAccessChildOfCollection() {
		String normalPathToERS = "";
		String referencePathToERS = "";
		String code = "";
		String callPathFromDomainObjectToERS = "";
		String callPathFromReferenceToERS = "";

		assertEquals("ERS", code);
		assertEquals(referencePathToERS, normalPathToERS);
		assertNotEquals(callPathFromReferenceToERS, callPathFromDomainObjectToERS);
	}
}
