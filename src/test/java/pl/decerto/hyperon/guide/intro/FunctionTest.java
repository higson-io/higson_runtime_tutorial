package pl.decerto.hyperon.guide.intro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.smartparam.engine.core.context.DefaultContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class FunctionTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	@Test
	void shouldCallSimpleFunctionAndGetValue() {

		Object result = hyperonEngine.call("demo.motor.util.drivingLicenceYears", buildFunctionContext());

		int drivingLicenceYears = (int) result;

		assertEquals(6, drivingLicenceYears);
	}

	/**
	 * 1. Create proper context for function.
	 * 2. Call function named "demo.insurance.calcpremium" with created context.
	 * 3. Cast it to double and compare result.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldCallSimpleFunctionAndGetValue() {

		double calculatedPremium = 0;

		assertEquals(6.0, calculatedPremium);
	}

	private DefaultContext buildFunctionContext() {
		return new HyperonContext().with("quote.driver.age", 25).with("quote.driver.licenceObtainedAtAge", 19);
	}
}
