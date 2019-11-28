package pl.decerto.hyperon.guide.parameters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * There is possibility for returning null value from matrix. For that to happen,
 * parameter must bo configured in studio with flag "Match required" to false.
 * When it is set up, Hyperon Engine will not throw exception on not matched
 * context and will return {@code null}.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class NullableValueInMatrixParameterTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	@Test
	void shouldGetEmptyParamValueForMissingCodeFromParameter() {
		HyperonContext ctx = new HyperonContext("quote.driver.gender", "A");

		ParamValue paramValue = hyperonEngine.get("tutorial.enum.example", ctx);

		assertEquals(0, paramValue.size());
		assertTrue(paramValue.isEmpty());  // better check if size of matched rows is 0
		assertTrue(paramValue.isBlank());  // can be used for checking empty rows and cells as well, not only whole paramValue

		assertNull(paramValue.getString());
		assertNull(paramValue.get());
		assertNull(paramValue.get(String.class));

	}

	/**
	 * <pre>
	 * 1. Create context for parameter matrix, using HyperonContext.
	 * 2. Get parameter called "tutorial.array.example".
	 * 3. Compare results:
	 * 	 a) Check number of matched rows using size() on instance of ParamValue
	 * 	 b) Check if paramValue is isEmpty() and assign it to result1
	 * 	 c) Check if paramValue is isBlank() and assign it to result2
	 * 	 d) Check if paramValue getString() is null and assign it to result3
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetArrayOfStringFromParameter() {
		int matchedRows = 1;

		boolean result1 = false;
		boolean result2 = false;
		String result3 = "";

		assertEquals(0, matchedRows);
		assertTrue(result1);
		assertTrue(result2);

		assertNull(result3);
	}

}
