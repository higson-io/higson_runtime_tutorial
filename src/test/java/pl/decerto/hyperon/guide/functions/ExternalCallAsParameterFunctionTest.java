package pl.decerto.hyperon.guide.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * There is also a possibility of passing complex object that are not supported
 * by Hyperon. This test will show how to call a function and pass any object.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class ExternalCallAsParameterFunctionTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	@Test
	void shouldCallFunctionWithCustomClass() {

		User user = new User("Jan", "Kowalski");

		String result = (String) hyperonEngine.call("tutorial.external.class.function", new HyperonContext(), user);
		assertEquals("Function call for user:Jan Kowalski", result);
	}

	/**
	 * 1. Extend class user with field "age" as Integer and expose it with getAge()
	 * 2. Call function 'tutorial.external.class.function2'
	 * 3. Compare result as String
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldCallFunctionWithCustomClass() {
		String result = "";
		assertEquals("Function call for user:Jan Kowalski 15", result);
	}

}
