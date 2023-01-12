package pl.decerto.hyperon.guide.administration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.model.HyperonDomainObject;
import pl.decerto.hyperon.runtime.rhino.RhinoDate;

/**
 * <p>This section helps developer to understand concept of regions, versions and scheduling(timeline) in Hyperon. Three most important parts of Hyperon:
 * parameters, functions and domain elements can be assigned to region and version. Based on created region and multiple versions, scheduling(timeline) can
 * be created.
 * </p>
 * <p>
 *  <b>Warning!!</b><br>
 *	Assigning domain element to region is a little different, than for parameters/functions. Domain elements have tree structure so whenever domain
 *	element is assigned to region and version, all child elements of it will also be assigned automatically to the same region and version. Hyperon Runtime
 *	is responsible for providing proper domain configuration structure for "active" region/version.
 * </p>
 * <p>
 *  Chaining Hyperon components will also invoke them in active region/version or global components.
 *  <br>Example: if domain element is attached to region: "REG"
 *  and version: 2, also to it's attribute "is_active", function is attached, then if function is not global, it will try to call currently "active" function.
 * </p>
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
@Disabled("test not working") //TODO: MPP-5621
class AssignedElementsToRegionTest {

	private static final String MY_REGION = "MyRegion";

	@Autowired
	private HyperonEngine hyperonEngine;

	@Value("${hyperon.profile}")
	private String profile;

	/**
	 * This test presents how to call different versions of same function based on Timeline defined in Hyperon Studio.
	 */
	@Test
	void shouldCallProperVersionOfFunctionInRegion_basedOnSetEffectiveDate() {
		hyperonEngine.clearEffectiveSetup();        //FIXME: remove it, when MPP-2527 will be fixed
		HyperonContext context = new HyperonContext("input", "My test input");

		// setting effective date so based on defined timeline it will be:  version "1" of "MyRegion"
		hyperonEngine.setEffectiveDate(RhinoDate.getDate(2019, 3, 16));

		// it will call function that is assigned to region/version based on configuration in Hyperon Studio - "Timeline"
		String resultVersion1 = (String) hyperonEngine.call("tutorial.versionable.function", context);
		assertEquals("My test input from region MyRegion and version 1", resultVersion1);

		// setting effective date so based on defined timeline it will be:  version "2" of "MyRegion"
		hyperonEngine.setEffectiveDate(RhinoDate.getDate(2019, 5, 18));

		// it will call function that is assigned to region/version based on schedule
		String resultVersion2 = (String) hyperonEngine.call("tutorial.versionable.function", context);
		assertEquals("My test input from region MyRegion and version 2", resultVersion2);

		hyperonEngine.clearEffectiveDate(); // this will remove effective date from next calls to Hyperon Engine
	}

	/**
	 * This test presents how to call different versions of same function based on directly setting "effective" version for region.
	 */
	@Test
	void shouldCallProperVersionOfFunctionInRegion_basedOnSetEffectiveVersion() {
		HyperonContext context = new HyperonContext("input", "My test input");
		// setting version to "1" of "MyRegion"
		hyperonEngine.setEffectiveVersion(MY_REGION, "1");

		// it will call function that is assigned to region/version based on configuration in Hyperon Studio - "MyView"
		String resultVersion1 = (String) hyperonEngine.call("tutorial.versionable.function", context);
		assertEquals("My test input from region MyRegion and version 1", resultVersion1);

		// setting version to "2" of "MyRegion"
		hyperonEngine.setEffectiveVersion(MY_REGION, "2");

		// it will call function that is assigned to region/version based on schedule
		String resultVersion2 = (String) hyperonEngine.call("tutorial.versionable.function", context);
		assertEquals("My test input from region MyRegion and version 2", resultVersion2);

		hyperonEngine.clearEffectiveVersion(MY_REGION); // this will setup MyRegion to default version based on Hyperon Studio - "MyView"
	}

	/**
	 * This test presents how to call different versions of same domain object path based on directly setting "effective" version for region.
	 */
	@Test
	void shouldGetProperVersionOfDomainInRegion_basedOnSetEffectiveVersion() {
		hyperonEngine.setEffectiveVersion(MY_REGION, "1");
		HyperonDomainObject domainV1 = hyperonEngine.getDomain(profile, "PLANS[LIAB]/DISCOUNTS[DISC]");

		assertFalse(domainV1.getAttr("available").getBoolean(null));

		hyperonEngine.setEffectiveVersion(MY_REGION, "2");

		HyperonDomainObject domainV2 = hyperonEngine.getDomain(profile, "PLANS[LIAB]/DISCOUNTS[DISC]");

		assertTrue(domainV2.getAttr("available").getBoolean(null));

		hyperonEngine.clearEffectiveVersions();
	}

	/**
	 * 1. Create parameter named: "tutorial.versionable.param", which:
	 * 	a) has 1 out column defined as String,
	 * 	b) provide 1 value: "My first region",
	 * 	c) assign this parameter to region: "MyRegion" and version: "1",
	 * 2. Create parameter named: "tutorial.versionable.param" (or clone parameter from point 1), which:
	 * 	a) has 1 out column defined as String,
	 * 	b) provide 1 value: "My second region",
	 * 	c) assign this parameter to region: "MyRegion" and version: "2",
	 * 3. Set effective version to "1", just in case since the timeline might be different already for this test.
	 * 4. Get value from parameter and assign it to result1.
	 * 5. Change effective version to 2.
	 * 6. Get value from parameter and assign it to result2.
	 * 7. Clear effective versions.
	 */
	@Disabled
	void todo_shouldCallProperVersionOfParameterInRegion_basedOnSetEffectiveVersion() {

		String result1 = "";
		String result2 = "";

		assertEquals("My first region", result1);
		assertEquals("My second region", result2);
	}

}
