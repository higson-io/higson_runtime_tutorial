package pl.decerto.hyperon.guide.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.model.HyperonDomainAttribute;
import pl.decerto.hyperon.runtime.model.HyperonDomainObject;

@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class CollectionDomainObjectTest {

	@Value("${hyperon.profile}")
	private String profile;

	@Autowired
	private HyperonEngine engine;

	@Test
	void shouldDirectlyAccessChildOfCollection() {
		HyperonDomainObject fullDO = engine.getDomain(profile, "/PLANS[FULL]");

		HyperonDomainAttribute missingAttribute = fullDO.getAttr("missing_attribute");

		assertNull(missingAttribute);

		HyperonDomainObject discountDE = fullDO.getChild("DISCOUNTS", "DE");
		HyperonDomainAttribute positionDE = discountDE.getAttribute("position");

		HyperonDomainObject discountGD = fullDO.getChild("DISCOUNTS", "GD");
		HyperonDomainAttribute positionGD = discountGD.getAttribute("position");

		assertEquals("/PLANS[FULL]/DISCOUNTS[DE]", discountDE.getPath());
		assertEquals(1, positionDE.getInteger(null).intValue());

		assertEquals("/PLANS[FULL]/DISCOUNTS[GD]", discountGD.getPath());
		assertEquals(2, positionGD.getInteger(null).intValue());
	}

	@Test
	void shouldIterateOverDiscountsAndMatchPositionAttributes() {
		HyperonDomainObject fullDO = engine.getDomain(profile, "/PLANS[FULL]");

		HyperonDomainAttribute missingAttribute = fullDO.getAttr("missing_attribute");

		assertNull(missingAttribute);

		List<HyperonDomainObject> discounts = fullDO.getChildren("DISCOUNTS");
		assertEquals(2, discounts.size());

		for (HyperonDomainObject discount : discounts) {
			if ("DE".equals(discount.getCode())) {
				assertEquals("/PLANS[FULL]/DISCOUNTS[DE]", discount.getPath());
				HyperonDomainAttribute position = discount.getAttribute("position");
				assertEquals(1, position.getInteger(null).intValue());
			} else if ("GD".equals(discount.getCode())) {
				assertEquals("/PLANS[FULL]/DISCOUNTS[GD]", discount.getPath());
				HyperonDomainAttribute position = discount.getAttribute("position");
				assertEquals(2, position.getInteger(null).intValue());
			}
		}
	}

	/**
	 * 1. Get proper domain object using path {@code PLANS -> FULL -> OPTIONS -> GOLD}.
	 * 2. Get attribute "ORDER".
	 * 3. Compare result:
	 * 	a) Get path to gold domain object
	 * 	b) Get ORDER attribute name
	 * 	c) Get value of ORDER attribute
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldFindChildOfCollectionUsingDirectAccess() {
		String pathToGoldDO = "";
		String orderAttributeName = "";
		int orderAttributeValue = -1;

		assertEquals("/PLANS[FULL]/OPTIONS[GOLD]", pathToGoldDO);
		assertEquals("Display order", orderAttributeName);
		assertEquals(3, orderAttributeValue);
	}

	/**
	 * 1. Get proper domain parent object {@code PLANS -> FULL}.
	 * 2. Get child named "DISCOUNTS" - it will return list of domain objects.
	 * 3. Iterate over list and aggregate by attribute "value". hint: might as well use streams
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldSumDiscountAmounts() {
		String pathToFullDO = "";
		double sumOfValue = 0.0;

		assertEquals("/PLANS[FULL]", pathToFullDO);
		assertEquals(35.0, sumOfValue);
	}
}
