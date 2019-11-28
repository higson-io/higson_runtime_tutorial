package pl.decerto.hyperon.guide.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.model.HyperonDomainAttribute;
import pl.decerto.hyperon.runtime.model.HyperonDomainObject;
import pl.decerto.hyperon.runtime.model.MpDomainAttributeDto;

/**
 * This tests helps to understand how attributes of domain object work. Attribute may contain not only
 * "raw" value, but can also have a function or parameter attached. Of course, if a function or a parameter
 * is attached, proper context (HyperonContext) must be provided.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class ComplexAttributeDomainObjectTest {

	@Value("${hyperon.profile}")
	private String profile;

	@Autowired
	private HyperonEngine engine;

	/**
	 * Example of fetching attribute with raw value.
	 */
	@Test
	void shouldGetRawAttributeOfDomainObjectUsingFullPath() {
		HyperonDomainObject biDO = engine.getDomain(profile, "/PLANS[FULL]/COVERAGES[BI]");

		assertTrue(biDO.isAttrSet("DESCRIPTION"));

		HyperonDomainAttribute descriptionAttr = biDO.getAttribute("DESCRIPTION");

		assertEquals("DESCRIPTION", descriptionAttr.getCode());
		assertEquals("Coverage description", descriptionAttr.getName());
		assertEquals("Bodily injury liability provides protection if you injure or kill someone while operating your car.",
			descriptionAttr.getString(new HyperonContext()));

		assertEquals(MpDomainAttributeDto.RawType.LITERAL,
			descriptionAttr.getRawType());  // if value is directly written in attribute, then it will be marked as LITERAL
		assertEquals("Bodily injury liability provides protection if you injure or kill someone while operating your car.",
			descriptionAttr.getRawValue());

		assertEquals(biDO, descriptionAttr.getDomainObject());
	}

	/**
	 * Example of attribute with parameter attached to a value.
	 */
	@Test
	void shouldGetParameterAttributeOfDomainObjectUsingFullPath() {
		HyperonDomainObject biDO = engine.getDomain(profile, "/PLANS[FULL]/COVERAGES[BI]");

		assertTrue(biDO.isAttrSet("IS_AVAILABLE"));

		HyperonDomainAttribute isAvailableAttr = biDO.getAttribute("IS_AVAILABLE");

		HyperonContext context = exampleContext();

		assertEquals("IS_AVAILABLE", isAvailableAttr.getCode());
		assertEquals("Whether this coverage is available for current option", isAvailableAttr.getName());
		assertTrue(isAvailableAttr.getBoolean(context));

		assertEquals(MpDomainAttributeDto.RawType.PARAMETER,
			isAvailableAttr.getRawType());  // if value is directly written in attribute, then it will be marked as LITERAL
		assertEquals("demo.motor.coverage.availability",
			isAvailableAttr.getRawValue());

		assertEquals(biDO, isAvailableAttr.getDomainObject());
	}

	/**
	 * Example of attribute with function attached to a value.
	 */
	@Test
	void shouldGetFunctionAttributeOfDomainObjectUsingFullPath() {
		HyperonDomainObject biDO = engine.getDomain(profile, "/PLANS[FULL]/COVERAGES[BI]");

		assertTrue(biDO.isAttrSet("PREMIUM"));

		HyperonDomainAttribute premiumAttr = biDO.getAttribute("PREMIUM");

		HyperonContext context = exampleContext();

		assertEquals("PREMIUM", premiumAttr.getCode());
		assertEquals("Base premium for this coverage", premiumAttr.getName());
		assertEquals(50.7, premiumAttr.getNumber(context));

		assertEquals(MpDomainAttributeDto.RawType.FUNCTION,
			premiumAttr.getRawType());  // if value is directly written in attribute, then it will be marked as LITERAL
		assertEquals("demo.motor.coverage.bi.calculatePremium",
			premiumAttr.getRawValue());

		assertEquals(biDO, premiumAttr.getDomainObject());
	}

	private HyperonContext exampleContext() {
		return new HyperonContext("coverage.code", "BI", "option.code", "BRONZE", "quote.driver.gender", "F", "quote.driver.age", 20);
	}

	/**
	 * 1. Create proper hyperon context.
	 * 2. Get proper domain object for {@code PLANS -> FULL -> COVERAGES -> PD}
	 * 3. Compare results for attribute "DESCRIPTION":
	 * 	a) Check if attribute exists
	 * 	b) Get code of attribute
	 * 	c) Get value of attribute
	 * 	d) Get raw type of attribute's value
	 * 	e) Get raw value
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetRawAttributeOfDomainObjectUsingFullPath() {

		boolean isAttributeDescriptionSet = false;
		String descriptionCode = "";
		String descriptionName = "";
		String descriptionValue = "";
		MpDomainAttributeDto.RawType rawType = null;
		String rawValue = "";

		assertTrue(isAttributeDescriptionSet);

		assertEquals("DESCRIPTION", descriptionCode);
		assertEquals("Coverage description", descriptionName);
		assertEquals("Property damage liability protects you if your car damages someone else's property. " +
				"It also provides you with legal defense if another party files a lawsuit against you.",
			descriptionValue);

		assertEquals(MpDomainAttributeDto.RawType.LITERAL, rawType);
		assertEquals("Property damage liability protects you if your car damages someone else's property. " +
				"It also provides you with legal defense if another party files a lawsuit against you.",
			rawValue);
	}

	/**
	 * 1. Create proper hyperon context.
	 * 2. Get proper domain object for {@code PLANS -> FULL -> COVERAGES -> PD}
	 * 3. Compare results for attribute "POSITION":
	 * 	a) Check if attribute exists
	 * 	b) Get code of attribute
	 * 	c) Get value of attribute
	 * 	d) Get raw type of attribute's value
	 * 	e) Get raw value
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetParameterAttributeOfDomainObjectUsingFullPath() {
		boolean isAttributePositionSet = false;
		String positionCode = "";
		String positionName = "";
		int positionValue = 0;
		MpDomainAttributeDto.RawType rawType = null;
		String rawValue = "";

		assertTrue(isAttributePositionSet);

		assertEquals("POSITION", positionCode);
		assertEquals("Position on the screen or printout  (1, 2, ...)", positionName);
		assertEquals(2, positionValue);

		assertEquals(MpDomainAttributeDto.RawType.PARAMETER, rawType);
		assertEquals("demo.motor.coverage.position", rawValue);
	}

	/**
	 * 1. Create proper hyperon context.
	 * 2. Get proper domain object for {@code PLANS -> FULL -> COVERAGES -> UMPD}
	 * 3. Compare results for attribute "PREMIUM":
	 * 	a) Check if attribute exists
	 * 	b) Get code of attribute
	 * 	c) Get value of attribute
	 * 	d) Get raw type of attribute's value
	 * 	e) Get raw value
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetFunctionAttributeOfDomainObjectUsingFullPath() {
		boolean isAttributePremiumSet = false;
		String premiumCode = "";
		String premiumName = "";
		int premiumValue = 0;
		MpDomainAttributeDto.RawType rawType = null;
		String rawValue = "";

		assertTrue(isAttributePremiumSet);

		assertEquals("PREMIUM", premiumCode);
		assertEquals("Base premium for this coverage", premiumName);
		assertEquals(10.11, premiumValue);

		assertEquals(MpDomainAttributeDto.RawType.FUNCTION, rawType);
		assertEquals("demo.motor.plan.full.calculatePremium", rawValue);
	}

}
