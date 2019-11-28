package pl.decerto.hyperon.guide.context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.guide.context.adapter.DriverAdapter;
import pl.decerto.hyperon.guide.context.adapter.QuoteAdapter;
import pl.decerto.hyperon.guide.context.model.Driver;
import pl.decerto.hyperon.guide.context.model.Quote;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * In this section you will learn a new way of creating Hyperon Context using adapters approach.
 * In Hyperon Runtime there are classes which help to work with this:
 * <ol>
 *     <li>{@link pl.decerto.hyperon.ext.adapter.Adapter} - basic adapter, which is actually a Hyperon subcontext</li>
 *     <li>{@link pl.decerto.hyperon.ext.adapter.CollectionAdapter} - adapter for collection of adapters</li>
 *     <li>{@link pl.decerto.hyperon.ext.adapter.Mapping} - storage for mapping between context's subpaths and simple fields or nested
 *     objects/adapters</li>
 * </ol>
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class SimpleCustomContextWithAdaptersTest {

	@Value("${hyperon.profile}")
	private String profile;

	@Autowired
	private HyperonEngine engine;

	enum Gender {
		FEMALE, MALE
	}

	@Test
	void shouldGetValueFromParameterUsingContextAsAdapter() {
		Driver driver = new Driver()
			.setFirstName("John")
			.setLastName("Potter")
			.setGender("M")
			.setDateOfBirth(dateOfBirth());

		Quote quote = new Quote("FULL", driver);

		MotorContext context = new MotorContext(quote);

		assertTrue(context.get("quote.driver") instanceof DriverAdapter);
		assertEquals("John", context.getString("quote.driver.firstname"));
		assertEquals("Potter", context.getString("quote.driver.lastname"));
		assertEquals("M", context.getString("quote.driver.gender"));
		assertEquals(dateOfBirth(), context.getDate("quote.driver.dateofbirth"));
		assertEquals(22, context.getInteger("quote.driver.age").intValue());

		ParamValue entry = engine.get("demo.motor.coverage.pd.premium", context);
		BigDecimal factor = entry.get("factor");

		assertEquals(new BigDecimal("112.96"), factor);
	}

	/**
	 * 1. Build objects of quote, driver and pass them to {@code MotorContext}.
	 * 2. Get parameter: "tutorial.enum.example" and pass created context.
	 * 3. Compare result as enum.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetValueFromParameterUsingContextAsAdapter() {
		String firstName = "";
		String lastName = "";
		String gender = "";

		assertEquals("Marta", firstName);
		assertEquals("Borek", lastName);
		assertEquals("F", gender);

		Gender result = null;

		assertEquals(Gender.FEMALE, result);
	}

	/**
	 * 1. Add extra Integer field - "licenceObtainedAtAge" to {@link Driver}. Also getter/setter.
	 * 2. Provide proper mapping for subpath - "licenceObtainedAtAge" in {@link DriverAdapter}.
	 * 3. Build objects of quote, driver and pass them to {@code MotorContext}.
	 * 4. Call function "demo.motor.util.drivingLicenceYears".
	 * 5. Compare result as Integer.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldCallFunctionUsingContextAsAdapter() {
		int drivingLicenceYears = 0;

		assertEquals(4, drivingLicenceYears);
	}

	/**
	 * 1. Create new "Coverage" class.
	 * 	a) add field: String code
	 * 	b) add field: BigDecimal limit1
	 * 	c) create getters/setters
	 * 2. Add new coverage property to {@link Quote} with getter/setter.
	 * 3. Create adapter class for Coverage.
	 * 	a) create proper mapping for all Coverage fields
	 * 4) Add new mapping for coverage adapter within {@link QuoteAdapter}.
	 * 5) Create proper context with required values for function call >> "demo.motor.plan.full.calculatePremium".
	 * 6) Call function with context.
	 * 7) Compare result.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldCallFunctionForCoveragesContextAdAdapter() {
		double result = 0.0;
		assertEquals(0.12, result, 0.00001);
	}

	private Date dateOfBirth() {
		return Date.from(LocalDate.of(1997, Month.APRIL, 15).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

}
