package pl.decerto.hyperon.guide.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.smartparam.engine.core.context.DefaultContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * Function can call other Hyperon elements: parameters, functions and domain objects.
 * But nested calls may be dangerous and make context tree call complex, so
 * design them well and test them not only for correct results but also performance.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class ComplexFunctionWithNestedCallsTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	@Test
	void shouldCallComplexFunction() {
		DefaultContext ctx = new HyperonContext()
			.with("coverage.limit1", 3.4)        // directly access within function body
			.with("coverage.limit2", 5.1)        // directly access within function body
			.with("quote.driver.gender", "F")    // required by parameter call within function >> hyperon.getNumber('demo.motor.coverage.bi.tariff', ctx)
			.with("quote.driver.age", 23);       // required by parameter call within function >> hyperon.getNumber('demo.motor.coverage.bi.tariff', ctx)

		double result = (double) hyperonEngine.call("demo.motor.coverage.bi.calculatePremium", ctx);

		assertEquals(53.1, result);
	}

	/**
	 * 1. Create proper context for function.
	 * 2. Call function named "demo.motor.plan.full.calculatePremium" with created context.
	 * 3. Compare result and cast it to BigDecimal.
	 * Be aware that this function, calls parameter: "demo.motor.plan.full.tariff", for better understanding check function body.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldCallComplexFunction() {
		double result = 0.0;

		assertEquals(10.11, result);
	}
}
