package pl.decerto.hyperon.guide.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.model.HyperonDomainObject;

/**
 * Here you can learn about retrieving simple domain object and some basic configuration information about it.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class SimpleDomainObjectTest {

	@Value("${hyperon.profile}")
	private String profile;

	@Autowired
	private HyperonEngine engine;

	@Test
	void shouldGetBasicInformationAboutDomainObjectConfiguration() {
		HyperonDomainObject fullDO = engine.getDomain(profile, "/PLANS[FULL]");

		assertEquals("FULL", fullDO.getCode());
		assertEquals("/PLANS[FULL]", fullDO.getPath());
		assertEquals("Rating Plan for summer 2016", fullDO.getName());
		assertEquals("/", fullDO.getParent().getCallPath());  // parent is root directly
		assertNull(fullDO.getParent().getPath());  // parent is root directly
		assertEquals(0, fullDO.getAttributes().size()); // no attributes
		assertEquals(0, fullDO.getDynamicAttributes().size()); // no dynamic attributes
		assertEquals("RATING_PLAN", fullDO.getTypeCode());
		assertEquals(3, fullDO.getChildren("OPTIONS").size());
		assertEquals(9, fullDO.getChildren("COVERAGES").size());
		assertEquals(2, fullDO.getChildren("DISCOUNTS").size());
	}

	/**
	 * <pre>
	 * 1. Get Domain Object from profile = "DEMO", with proper path to {@code PLANS->FULL->COVERAGES->PD}
	 * 2. Compare some basic information about PD domain object:
	 * 	 a) Get code
	 * 	 b) Get path to domain object
	 * 	 c) Get name of domain object
	 * 	 d) Get parent code of domain object, hint: first get parent
	 * 	 e) Get number of defined attributes
	 * 	 f) Get number of dynamic attributes
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetBasicInformationAboutDomainObjectConfiguration() {
		String code = "";
		String path = "";
		String name = "";
		String parentCode = "";
		int attributesSize = -1;
		int dynamicAttributesSize = -1;

		assertEquals("PD", code);
		assertEquals("/PLANS[FULL]/COVERAGES[PD]", path);
		assertEquals("Property Damage Liability", name);
		assertEquals("FULL", parentCode);
		assertEquals(6, attributesSize);
		assertEquals(0, dynamicAttributesSize);
	}

}
