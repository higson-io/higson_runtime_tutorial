package pl.decerto.hyperon.guide.intro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.model.HyperonDomainObject;

/**
 * Whenever Hyperon Engine is calling for Domain Objects, the profile must be provided,
 * since domain definition/configuration is always associated with a particular profile.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class DomainTest {

	private static final String PROFILE = "DEMO";

	@Autowired
	private HyperonEngine engine;

	@Test
	void shouldGetDomainObject() {
		HyperonDomainObject bronzeDO = engine.getDomain(PROFILE, "/PLANS[FULL]/OPTIONS[BRONZE]");

		Integer orderNr = bronzeDO.getAttribute("ORDER").getInteger(new HyperonContext());

		assertEquals(1, orderNr.intValue());
		assertEquals(1, bronzeDO.getAttrInteger("ORDER", new HyperonContext()).intValue());
	}

	/**
	 * <pre>
	 * 1. Get Domain Object from profile = "DEMO", with proper path to "GOLD" option.
	 * 2. Create context for attribute, using HyperonContext.
	 * 3. Get "ORDER" attribute from retrieved Domain Object using created context.
	 * 4. Compare results:
	 * 	 a) Get value using: "getAttribute" and assign it to orderNr_case1
	 * 	 b) Get value using: "getAttrInteger" and assign it to orderNr_case2
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetDomainObject() {
		Integer orderNr_case1 = -1;
		Integer orderNr_case2 = -1;

		assertEquals(3, orderNr_case1.intValue());
		assertEquals(3, orderNr_case2.intValue());
	}

}
