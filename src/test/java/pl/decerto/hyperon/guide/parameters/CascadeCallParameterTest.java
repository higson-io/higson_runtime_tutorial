package pl.decerto.hyperon.guide.parameters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * This test will present how the value returned by function can be used
 * to select parameter row by matching the value against "IN" column of the parameter.
 * This allows to calculate INPUT for parameter based on
 * complex logic, not only retrieving simple value from context.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class CascadeCallParameterTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	@Test
	void cascadeCallToFunctionShouldReturnValueFromParameter() {
		HyperonContext context = new HyperonContext();
		context.set("quote.driver.age", 35);
		context.set("quote.driver.licenceObtainedAtAge", 25);

		ParamValue paramValue = hyperonEngine.get("demo.motor.discount.drivingExperience", context);

		assertTrue(paramValue.<Boolean>get("available"));
		assertEquals(new BigDecimal(20), paramValue.<BigDecimal>get("amountValue"));
	}

	/**
	 * <pre>
	 * 1. Create context for parameter matrix, using HyperonContext.
	 * 2. Get parameter called "demo.motor.discount.drivingExperience".
	 * 3. Compare results for columns: "available" and "amountValue".
	 * hint: Verify what function defined in INPUT for parameter source value.
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_cascadeCallToFunctionShouldReturnValueFromParameter() {
		Boolean available = Boolean.FALSE;
		BigDecimal amountValue = BigDecimal.ZERO;

		assertTrue(available);
		assertEquals(new BigDecimal(30), amountValue);
	}

}
