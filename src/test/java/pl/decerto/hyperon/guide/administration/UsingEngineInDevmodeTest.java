package pl.decerto.hyperon.guide.administration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.CustomHyperonIntegrationConfiguration;
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * Before running the tests you should properly set up the  Hyperon Engine in developer mode.
 * Go to {@link CustomHyperonIntegrationConfiguration} and fill in missing method code using guidance from comments.
 * You can review existing code from {@link HyperonIntegrationConfiguration}.
 *
 * Using Hyperon Engine with developer mode "ON" is transparent for developer. API remains the same, but developer using
 * runtime can access objects: parameter, function or domain, which are not published but remain in user's session.
 */
@Disabled("Remove this annotation when ready to run tests")
@SpringBootTest(classes = CustomHyperonIntegrationConfiguration.class)
class UsingEngineInDevmodeTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	/**
	 * 1. Find parameter in Hyperon Studio, which is not yet published and get it's full code.
	 * 2. Get column "test"(first column) as String.
	 * 3. Compare result.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetValueFromCreatedParameterButNotYetPublished() {
		int rowCount = 0;
		String parameterValue = "";

		assertEquals(1, rowCount);
		assertEquals("abc", parameterValue);
	}

	/**
	 * 1. Find domain object in Hyperon Studio, which is not yet published and get it's path.
	 * 2. Get attribute "value" as int.
	 * 3. Compare result.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetAttributeValueFromCreatedDomainObjectButNotYetPublished() {
		int result = 0;

		assertEquals(2, result);
	}

	/**
	 * 1. Find function in Hyperon Studio, which is not yet published and get is's full code.
	 * 2. Call function and cast result to String.
	 * 3. Compare result.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldCallFunctionWhichNotYetPublished() {
		String result = "";

		assertEquals("abc", result);
	}

}
