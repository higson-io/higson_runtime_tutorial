package pl.decerto.hyperon.guide.parameters;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * In this test cases you will learn about a little more complex values in matrix - arrays.
 * Arrays can hold multiple values of types supported by Hyperon in
 * one matrix cell. Access to this functionality can be achieved using:
 * <code>row().getStringArray(..) or row().getArray(..)</code> on {@link ParamValue}
 * instance. Accessing cell that holds arrays can be done as for "simple" types -
 * by index or unique column name.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class ArraysInMatrixParameterTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	@Test
	void shouldGetArrayOfStringFromParameter() {
		HyperonContext ctx = new HyperonContext("coverage.code", "BI");

		ParamValue paramValue = hyperonEngine.get("tutorial.array.example", ctx);

		String[] expectedResultColumn1 = {"BRONZE", "GOLD", "SILVER"};
		Integer[] expectedResultColumn2 = {1, 2, 3};

		assertEquals(1, paramValue.size());

		/* First column **/
		int firstColumnIndex = 0;
		assertArrayEquals(expectedResultColumn1, paramValue.row().getStringArray(firstColumnIndex));
		assertArrayEquals(expectedResultColumn1, paramValue.row().getStringArray("optionCodes"));
		assertEquals("BRONZE", paramValue.row().getArray(firstColumnIndex)[0].getValue());
		assertEquals("BRONZE", paramValue.row().getArray(firstColumnIndex)[0].getString());
		assertEquals("GOLD", paramValue.row().getArray(firstColumnIndex)[1].getValue());
		assertEquals("GOLD", paramValue.row().getArray(firstColumnIndex)[1].getString());
		assertEquals("SILVER", paramValue.row().getArray(firstColumnIndex)[2].getValue());
		assertEquals("SILVER", paramValue.row().getArray(firstColumnIndex)[2].getString());

		/* Second column **/
		int secondColumnIndex = 1;
		assertArrayEquals(expectedResultColumn2, paramValue.row().getIntegerArray(1));
		assertArrayEquals(expectedResultColumn2, paramValue.row().getIntegerArray("optionOrder"));
		assertEquals(1L, paramValue.row().getArray(secondColumnIndex)[0].getValue());
		assertEquals(1, paramValue.row().getArray(secondColumnIndex)[0].getInteger().intValue());
		assertEquals(2L, paramValue.row().getArray(secondColumnIndex)[1].getValue());
		assertEquals(2, paramValue.row().getArray(secondColumnIndex)[1].getInteger().intValue());
		assertEquals(3L, paramValue.row().getArray(secondColumnIndex)[2].getValue());
		assertEquals(3, paramValue.row().getArray(secondColumnIndex)[2].getInteger().intValue());
	}

	/**
	 * <pre>
	 * 1. Create context for parameter matrix, using HyperonContext.
	 * 2. Get parameter called "tutorial.array.example".
	 * 3. Compare results:
	 * 	 a) Check number of matched rows using size() on instance of ParamValue
	 * 	 b) Get value from ParamValue as String[] using: "paramValue.row().getStringArray(index)" and assign it to result1
	 * 	 c) Get value from ParamValue as String[] using: "paramValue.row().getStringArray(uniqueCode)" and assign it to result2
	 * 	 d) Get value from ParamValue as String using: "paramValue.row().getArray(column index)[array index].getString()" and assign it to result3
	 * 4. Verify values in the second column of the parameter
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetArrayOfStringFromParameter() {
		int matchedRows = 0;

		String[] expectedArray1 = {"GOLD", "SILVER"};
		Integer[] expectedArray2 = {1, 2};

		/* Verify first column of the parameter */
		String[] result1 = {};
		String[] result2 = {};
		String result3 = "";

		assertEquals(1, matchedRows);
		assertArrayEquals(expectedArray1, result1);
		assertArrayEquals(expectedArray1, result2);
		assertEquals("SILVER", result3);

		/* Verify second column of the parameter */
		Integer[] result4 = {};
		Integer[] result5 = {};
		int result6 = 0;

		assertArrayEquals(expectedArray2, result4);
		assertArrayEquals(expectedArray2, result5);
		assertEquals(2, result6);
	}

}
