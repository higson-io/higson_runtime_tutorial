package pl.decerto.hyperon.guide.intro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.smartparam.engine.core.context.DefaultContext;
import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class ParameterTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	@Test
	void shouldGetSimpleValueFromParameter() {

		DefaultContext ctx = new HyperonContext()
				.with("coverage.code", "BI")
				.with("option.code", "BRONZE");

		ParamValue paramRows = hyperonEngine.get("demo.motor.coverage.availability", ctx);

		assertEquals(1, paramRows.size());  // one row only
		assertTrue(paramRows.getBoolean());          // value is Boolean type
		assertTrue(paramRows.get(Boolean.class));    // value is Boolean type
	}

	/**
	 * <pre>
	 * 1. Create context for parameter matrix, using HyperonContext.
	 * 2. Get parameter called "demo.motor.coverage.bi.tariff".
	 * 3. Compare results:
	 * 	 a) Check number of matched rows using size() on instance of ParamValue
	 * 	 b) Get value from ParamValue as BigDecimal using: "getBigDecimal()" and assign it to result1
	 * 	 c) Get value from ParamValue as BigDecimal using: "paramRow.get(BigDecimal.class)" and assign it to result2
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetSimpleValueFromParameter() {
		int numberOfMatchedRows = 0;
		BigDecimal result1 = null;
		BigDecimal result2 = null;

		assertEquals(1, numberOfMatchedRows);  // one row only
		assertEquals(new BigDecimal("0.507"), result1);
		assertEquals(new BigDecimal("0.507"), result2);
	}

}
