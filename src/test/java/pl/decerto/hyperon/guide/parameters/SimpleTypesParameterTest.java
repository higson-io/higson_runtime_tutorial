package pl.decerto.hyperon.guide.parameters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.smartparam.engine.core.context.DefaultContext;
import org.smartparam.engine.core.output.MultiValue;
import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * In these test cases you will learn more about simple types of parameter result, supported by Hyperon.
 * Result of each call to Hyperon Engine for parameters is of {@link ParamValue} type.<br>
 * Supported simple types are:
 * <ul>
 *     <li>String</li>
 *     <li>Boolean</li>
 *     <li>Integer/Long</li>
 *     <li>BigDecimal</li>
 *     <li>Date</li>
 *     <li>Datetime</li>
 *     <li>Enum</li>
 * </ul>
 * More complex result types will be described in other test cases.
 * <p>
 *     There are multiple ways to access value from matrix. Most intuitive are methods with prefix "get"
 *     followed by one of supported types, like: "getString()". Other possibilities will be presented in
 *     test cases below.
 * </p>
 *
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class SimpleTypesParameterTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	enum Gender {
		FEMALE, MALE
	}

	@Test
	void shouldGetSimpleValueAsBooleanFromParameter() {
		DefaultContext ctx = new HyperonContext()
				.with("coverage.code", "BI")
				.with("option.code", "BRONZE");

		ParamValue paramRows = hyperonEngine
			.get("demo.motor.coverage.availability", ctx);

		assertEquals(1, paramRows.size());
		assertTrue(paramRows.getBoolean());         // getBoolean() methods on matrix will get value from first row and first column
		assertTrue(paramRows.<Boolean>get());       // <T>get() methods on matrix will get value from first row and first column
		assertTrue(paramRows.get(Boolean.class));   // get([class]) methods on matrix will get value from first row and first column
	}

	@Test
	void shouldGetSimpleValueAsBigDecimalFromParameter() {
		HyperonContext ctx = new HyperonContext("quote.driver.gender", "F", "quote.driver.age", 48);

		ParamValue paramRows = hyperonEngine.get("demo.motor.coverage.bi.tariff", ctx);

		BigDecimal expected = new BigDecimal("0.656");

		assertEquals(1, paramRows.size());
		assertEquals(expected, paramRows.getBigDecimal());
		assertEquals(expected, paramRows.get(BigDecimal.class));
		assertEquals(expected, paramRows.<BigDecimal>get());
	}

	@Test
	void shouldGetSimpleValueAsIntegerFromParameter() {
		HyperonContext ctx = new HyperonContext("coverage.code", "PIP");

		String uniqueColumnCode = "position";

		ParamValue paramRows = hyperonEngine.get("demo.motor.coverage.position", ctx);

		int expected = 4;

		assertEquals(1, paramRows.size());
		assertEquals(expected, paramRows.getInteger().intValue());
		assertEquals(expected, paramRows.getInteger(uniqueColumnCode).intValue());
		assertEquals(expected, paramRows.getHolder().getLong().longValue());
//		assertEquals(expected, paramRow.<Integer>get().intValue());       // existing bug: [MPP-2492]
//		assertEquals(expected, paramRow.get(Integer.class).intValue());   // existing bug: [MPP-2492]
		assertEquals(expected, paramRows.get(Long.class).intValue());      // workaround
		assertEquals(expected, paramRows.<Long>get().intValue());           // workaround
		assertEquals(expected, paramRows.get(uniqueColumnCode, Long.class).intValue());
		assertEquals(expected, paramRows.get(Long.class).longValue());
	}

	@Test
	void shouldGetSimpleValueAsStringFromParameters() {
		HyperonContext ctx = new HyperonContext("coverage.code", "PIP");
		String uniqueColumnCode = "name";

		ParamValue paramRows = hyperonEngine.get("demo.motor.coverage.description", ctx);

		assertEquals(1, paramRows.size());                    // one row only

		String expectedCodeName = "Personal Injury Protection";
		assertEquals(1, paramRows.size());
		assertEquals(expectedCodeName, paramRows.get(String.class));
		assertEquals(expectedCodeName, paramRows.get());
		assertEquals(expectedCodeName, paramRows.getString());
		assertEquals(expectedCodeName, paramRows.<String>get());
		assertEquals(expectedCodeName, paramRows.get(uniqueColumnCode));
		assertEquals(expectedCodeName, paramRows.get(0));
		assertEquals(expectedCodeName, paramRows.get(0, String.class));
		assertEquals(expectedCodeName, paramRows.get(uniqueColumnCode, String.class));
	}

	@Test
	void shouldGetSimpleValueAsDateFromParameters() {
		HyperonContext ctx = new HyperonContext("quote.planCode", "BI");
		String uniqueColumnCode = "customDate";

		ParamValue paramRows = hyperonEngine.get("tutorial.date.example", ctx);

		Date expectedDatetime = getDate(2019, 4, 2);

		assertEquals(1, paramRows.size());
		assertEquals(expectedDatetime, paramRows.getDate());
		assertEquals(expectedDatetime, paramRows.get());
		assertEquals(expectedDatetime, paramRows.get(Date.class));
		assertEquals(expectedDatetime, paramRows.<Date>get());
		assertEquals(expectedDatetime, paramRows.get(uniqueColumnCode));
		assertEquals(expectedDatetime, paramRows.get(0));
		assertEquals(expectedDatetime, paramRows.get(0, Date.class));
		assertEquals(expectedDatetime, paramRows.get(uniqueColumnCode, Date.class));
	}

	@Test
	void shouldGetSimpleValueAsDatetimeFromParameters() {
		HyperonContext ctx = new HyperonContext("quote.planCode", "BI");
		String uniqueColumnCode = "customDatetime";

		ParamValue paramRows = hyperonEngine.get("tutorial.datetime.example", ctx);

		Date expectedDatetime = getDateTime(2019, 4, 2, 14, 50, 55);

		assertEquals(1, paramRows.size());
		assertEquals(expectedDatetime, paramRows.getDatetime());
		assertEquals(expectedDatetime, paramRows.get());
		assertEquals(expectedDatetime, paramRows.get(Date.class));
		assertEquals(expectedDatetime, paramRows.<Date>get());
		assertEquals(expectedDatetime, paramRows.get(uniqueColumnCode));
		assertEquals(expectedDatetime, paramRows.get(0));
		assertEquals(expectedDatetime, paramRows.get(0, Date.class));
		assertEquals(expectedDatetime, paramRows.get(uniqueColumnCode, Date.class));
	}

	@Test
	void shouldGetSimpleValueAsEnumFromParameters() {
		HyperonContext ctx = new HyperonContext("quote.driver.gender", "F");
		String uniqueColumnCode = "value";

		ParamValue paramRows = hyperonEngine.get("tutorial.enum.example", ctx);

		assertEquals(1, paramRows.size());
		assertEquals(Gender.FEMALE, paramRows.getEnum(Gender.class));
		assertEquals(Gender.FEMALE, paramRows.getEnum(uniqueColumnCode, Gender.class));
	}

	@Test
	void shouldGetAllSimpleValuesFromParameter() {
		DefaultContext ctx = new HyperonContext().with("quote.planCode", "CASE1");

		ParamValue paramRows = hyperonEngine
			.get("tutorial.simple.types", ctx);

		Date expectedDate = getDate(2019, 4, 2);

		Date expectedDatetime = getDateTime(2019, 4, 8, 9, 22, 15);

		MultiValue row = paramRows.row();

		assertEquals(1, paramRows.size());
		assertEquals(expectedDate, row.getDate("mydate"));
		assertEquals(expectedDatetime, row.getDatetime("mydatetime"));
		assertEquals(new BigDecimal("22.43"), row.getBigDecimal("mynumber"));
		assertEquals(32, row.getInteger("myinteger").intValue());
		assertFalse(paramRows.getBoolean("myboolean"));
	}

	/**
	 * <pre>
	 * 1. Create context for parameter matrix, using HyperonContext.
	 * 2. Get parameter called "demo.motor.coverage.description".
	 * 3. Compare results:
	 * 	 a) Check number of matched rows using size() on instance of ParamValue
	 * 	 b) Get value from ParamValue as String using: "paramRow.get(String.class)" and assign it to result1
	 * 	 c) Get value from ParamValue as String using: "paramRow.<String>get()" and assign it to result2
	 * 	 d) Get value from ParamValue as String using: "paramRow.getString()" and assign it to result3
	 * 	 e) Get value from ParamValue as String using: "paramRow.get()" and assign it to result4
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetSimpleValueAsStringFromParameters() {

		int matchedRows = 0;
		String result1 = "";
		String result2 = "";
		String result3 = "";
		String result4 = "";

		String expectedCodeName = "Personal Injury Protection";

		assertEquals(1, matchedRows);
		assertEquals(expectedCodeName, result1);
		assertEquals(expectedCodeName, result2);
		assertEquals(expectedCodeName, result3);
		assertEquals(expectedCodeName, result4);
	}

	/**
	 * This test case checks from parameter: "tutorial.simple.types" all supported types (except String).
	 * 1. Create proper context for case 2
	 * 2. Get parameter called: "tutorial.simple.types"
	 * 3. Compare results for each type, either by column name or index.
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetAllSimpleValuesFromParameter() {
		DefaultContext ctx = new HyperonContext().with("quote.planCode", "CASE2");

		int matchedRows = 0;
		Date result1 = null;  // date
		Date result2 = null;  // datetime
		BigDecimal result3 = null;
		int result4 = 0;
		boolean result5 = false;

		Date expectedDate = getDate(2019, 4, 8);
		Date expectedDatetime = getDateTime(2019, 4, 8, 10, 24, 56);

		assertEquals(1, matchedRows);
		assertEquals(expectedDate, result1);
		assertEquals(expectedDatetime, result2);
		assertEquals(new BigDecimal("123.4"), result3);
		assertEquals(15, result4);
		assertTrue(result5);
	}

	/**
	 * <pre>
	 * 1. Create context for parameter matrix, using HyperonContext.
	 * 2. Get parameter called "tutorial.enum.example".
	 * 3. Compare results:
	 * 	 a) Check number of matched rows using size() on instance of ParamValue
	 * 	 b) Get value from ParamValue as Enum using: "paramRow.getEnum(Gender.class)" and assign it to result1
	 * 	 c) Get value from ParamValue as Enum using: "paramRow.getEnum(uniqueCode, Gender.class)" and assign it to result2
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetSimpleValueAsEnumFromParameters() {
		int matchedRows = 0;
		Gender result1 = null;
		Gender result2 = null;

		assertEquals(1, matchedRows);
		assertEquals(Gender.MALE, result1);
		assertEquals(Gender.MALE, result2);
	}

	private Date getDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second) {
		LocalDateTime ofDatetime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
		return Date.from(ofDatetime.atZone(ZoneId.systemDefault()).toInstant());
	}

	private Date getDate(int year, int month, int dayOfMonth) {
		LocalDate ofDate = LocalDate.of(year, month, dayOfMonth);
		return Date.from(ofDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
