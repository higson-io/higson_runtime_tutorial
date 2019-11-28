package pl.decerto.hyperon.guide.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.model.HyperonDomainObject;
import pl.decerto.hyperon.runtime.model.HyperonDomainObjectType;

/**
 * The following test illustrates how to obtain information about the type of domain object.
 *
 * The relation between domain types and domain objects is similar to the relation between
 * classes and objects in Java. In Hyperon Studio domain types are created and modified on
 * 'Domain definition' screen, and domain objects on screen 'Domain configuration'
 *
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class DomainTypeTest {

	@Value("${hyperon.profile}")
	private String profile;

	@Autowired
	private HyperonEngine engine;

	@Test
	void shouldHaveAccessToBasicInformationAboutTypeOfElement() {
		HyperonDomainObject collDO = engine.getDomain(profile, "/PLANS[FULL]/COVERAGES[COLL]");

		assertEquals("COVERAGE", collDO.getTypeCode());
		assertEquals("Risk Coverage", collDO.getTypeName());

		HyperonDomainObjectType typeCollection = collDO.getTypeCollection();
		assertEquals("COVERAGES", typeCollection.getCode());
		assertEquals(6, typeCollection.getAttributeDefinitions().size());
	}

	/**
	 * 1. Get domain element: {@code PLANS->FULL->OPTIONS->SILVER}
	 * 2. Compare some basic information about type of element:
	 * 	a) Get type code
	 * 	b) Get type name
	 * 	c) Get type collection code
	 * 	d) Get number of attributes
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldHaveAccessToBasicInformationAboutTypeOfElement() {
		HyperonDomainObject silverDO = engine.getDomain(profile, "/PLANS[FULL]/OPTIONS[SILVER]");

		String typeCode = "";
		String typeName = "";
		String typeCollectionCode = "";
		int numberOfAttributes = 0;

		assertEquals("OPTION", typeCode);
		assertEquals("Rating Option (Variation)", typeName);

		assertEquals("OPTIONS", typeCollectionCode);
		assertEquals(1, numberOfAttributes);
	}
}
