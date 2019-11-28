package pl.decerto.hyperon.guide.context;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.ext.adapter.CollectionAdapter;
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.guide.context.model.Option;
import pl.decerto.hyperon.guide.context.model.Quote;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * In this example you will learn how to combine Hyperon Context (using adapters) with collections, using
 * {@link CollectionAdapter}. For this let's examine {@link pl.decerto.hyperon.guide.context.adapter.QuoteAdapter} example.
 *
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class ContextWithCollectionAdaptersTest {

	@Value("${hyperon.profile}")
	private String profile;

	@Autowired
	private HyperonEngine engine;

	/**
	 * This test creates quote and multiple options assigned to quote, which is put then to context.
	 * Then function "tutorial.options.allSeparatedCodes" is called - this function iterates over collection of options
	 * (thanks to {@link CollectionAdapter}) and return all option codes.
	 */
	@Test
	void shouldReturnAllOptionCodesWithAdapterContext() {

		Quote quote = new Quote("FULL");
		quote.addOption(new Option("BRONZE"));
		quote.addOption(new Option("SILVER"));
		quote.addOption(new Option("GOLD"));

		MotorContext context = new MotorContext(quote);

		String allCodes = (String) engine.call("tutorial.options.allSeparatedCodes", context);
		assertEquals("BRONZE,SILVER,GOLD", allCodes);
	}

	/**
	 * 1. Use previously created {@code Coverage} class and apply it as {@code List<Coverage>} field to {@link Quote}.
	 * 2. Alter adapter {@link pl.decerto.hyperon.guide.context.adapter.QuoteAdapter} with new mapping for coverages -
	 * use {@code CollectionAdapter}.
	 * 3. Create new groovy function, that will iterate over 3 coverages within quote (context) and sum values from
	 * "limit1", returning BigDecimal.
	 * 4. Create context with values as presented below:
	 * 	a) coverage1 - code: "BI", limit1: 1.43
	 * 	b) coverage2 - code: "COLL", limit1: 2.21
	 * 	c) coverage3 - code: "ERS", limit1: 4.55
	 * 5. Compare result as BigDecimal
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldReturnSumLimit1OfThreeCoveragesWithAdapterContext() {
		BigDecimal result = null;

		assertEquals(new BigDecimal("8.19"), result);
	}

}
