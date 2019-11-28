package pl.decerto.hyperon.guide.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * Hyperon engine allows to call functions with parameters in two ways:
 * <ul>
 *     <li>all required values are passed within context - {@code hyperonEngine.call([function code], [context]) }</li>
 *     <li>context is not enough or too big to use, then there is possibility to pass arguments (from 0 to any number) directly to function call - {@code
 *     	  hyperonEngine.call([function code], [context], [arguments]....)
 *     }</li>
 * </ul>
 * <b>WARNING</b><br>
 * If function is defined with arguments but is called without any, then the values of arguments
 * passed to the function are null.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class CallWithArgumentFunctionTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	@Test
	void shouldCallFunctionWithArgument() {
		HyperonContext ctx = new HyperonContext();
		ctx.with("coverage.limit1", 3.4);
		ctx.with("coverage.limit2", 5.1);

		BigDecimal expectedResultForTrue = new BigDecimal("8.50");

		boolean canIncludeLimit2 = true;

		BigDecimal result = (BigDecimal) hyperonEngine.call("tutorial.simple.function.with.argument", ctx, canIncludeLimit2);

		assertEquals(expectedResultForTrue, result);
	}

	@Test
	void shouldCallFunctionWithNullArgument() {
		HyperonContext ctx = new HyperonContext();
		ctx.with("coverage.limit1", 3.4);
		ctx.with("coverage.limit2", 5.1);

		BigDecimal expectedResultForFalse = new BigDecimal("3.40");

		BigDecimal result = (BigDecimal) hyperonEngine.call("tutorial.simple.function.with.argument", ctx);

		// The programming language for Hyperon functions is Groovy.
		// In Groovy null has a boolean value of false
		assertEquals(expectedResultForFalse, result);
	}

	/**
	 * 1. Create proper context for function.
	 * 2. Call function named "tutorial.simple.function.with.argument" with created context and explicitly
	 * pass one boolean argument.
	 * 3. Cast it to BigDecimal and compare result.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldCallFunctionWithArgument() {

		BigDecimal expectedResultForFalse = new BigDecimal("3.40");

		BigDecimal result = BigDecimal.ONE;

		assertEquals(expectedResultForFalse, result);
	}

	/**
	 * 1. Create proper context for function "tutorial.simple.function.with.argument2" - verify body of this function in Hyperon Studio.
	 * 2. Call function named "tutorial.simple.function.with.argument2" with created context.
	 * 3. Cast it to String and compare result.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldCallFunctionWithNullArgument() {
		String result = "";

		assertEquals("Called function with: TESTaaaanull", result);
	}
}
