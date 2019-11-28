package pl.decerto.hyperon.guide.parameters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.smartparam.engine.core.output.MultiValue;
import org.smartparam.engine.core.output.ParamValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration;
import pl.decerto.hyperon.runtime.core.HyperonContext;
import pl.decerto.hyperon.runtime.core.HyperonEngine;

/**
 * In this section you will learn more about complex results of parameters matrix.
 * Sometimes parameters instead of mutliple values in "one row", return multiple column/rows -
 * a matrix. This matrix is represented in Hyperon as an instance of {@link ParamValue}.
 */
@SpringBootTest(classes = HyperonIntegrationConfiguration.class)
class MatrixParameterTest {

	@Autowired
	private HyperonEngine hyperonEngine;

	@Test
	void shouldGetMatrixFromParameter() {

		HyperonContext ctx = new HyperonContext("quote.vehicle.productionYear", "1970");

		ParamValue matrix = hyperonEngine.get("demo.motor.dict.vehicle.availableMakes", ctx);

		assertEquals(2, matrix.size());

		// using get on ParamValue object will always return data from the first row of matrix
		assertEquals("STAR", matrix.get(String.class));  // get() method on matrix will get value from first row and first column
		assertEquals("STAR", matrix.get(0, String.class));
		assertEquals("STAR", matrix.getString("make"));
		assertEquals(722, matrix.get(1, Long.class).longValue());
		assertEquals(722, matrix.getInteger("make_id").intValue());

		MultiValue firstRow = matrix.row();
		assertEquals("STAR", firstRow.getString("make"));  // first row with column code "make"
		assertEquals("STAR", matrix.get(0, 0, String.class));  // first column of first row
		assertEquals(722, firstRow.getInteger("make_id").intValue());  // first row with column code "make"
		assertEquals(722, matrix.get(0, 1, Long.class).intValue());  // second column of first row

		MultiValue secondRow = matrix.row(1);
		assertEquals("WARTBURG", secondRow.getString("make"));  // second row with column code "make"
		assertEquals("WARTBURG", matrix.get(1, 0, String.class));  // first column of second row
		assertEquals(230, secondRow.getInteger("make_id").intValue());  // second row with column code "make_id"
		assertEquals(230, matrix.get(1, 1, Long.class).intValue());  // second column of first row
	}

	@Test
	void shouldGetMatrixUsingIteratorFromParameter() {
		HyperonContext ctx = new HyperonContext("quote.vehicle.productionYear", "1970");

		ParamValue matrix = hyperonEngine.get("demo.motor.dict.vehicle.availableMakes", ctx);

		List<Integer> makerIds = new ArrayList<>();
		// this will iterate over each row and get value from second column as String
		for (MultiValue row : matrix) {
			makerIds.add(row.getInteger(1));
		}

		assertEquals(2, matrix.size());
		assertEquals(2, makerIds.size());
		assertTrue(makerIds.contains(722));
		assertTrue(makerIds.contains(230));
	}

	/**
	 * <pre>
	 * 1. Create context for parameter matrix, using HyperonContext, that will match assertions of this test.
	 * 2. Get parameter called "demo.motor.dict.vehicle.availableMakes".
	 * 3. Compare results for each row/column result.
	 * hint: use Tester in studio for this parameter
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetMatrixFromParameter() {
		int numberOfMatchedRows = 0;

		assertEquals(3, numberOfMatchedRows);

		String entry_0_0 = "";  // first row and first column
		int entry_0_1 = 0;  // first row and second column
		String entry_1_0 = "";  // second row and first column
		int entry_1_1 = 0;  // second row and second column
		String entry_2_0 = "";  // third row and first column
		int entry_2_1 = 0;  // third row and second column

		assertEquals("STAR", entry_0_0);
		assertEquals(722, entry_0_1);

		assertEquals("TRABANT", entry_1_0);
		assertEquals(221, entry_1_1);

		assertEquals("WARTBURG", entry_2_0);
		assertEquals(230, entry_2_1);
	}

	/**
	 * <pre>
	 * 1. Create context for parameter matrix, using HyperonContext, that will match assertions of this test.
	 * 2. Get parameter called "demo.motor.dict.vehicle.availableMakes".
	 * 3. Build list of values from unique column name "make" (first column)
	 * </pre>
	 */
	@Disabled("Remove this annotation when ready to run the test")
	@Test
	void todo_shouldGetMatrixUsingIteratorFromParameter() {

		List<String> makerNames = new ArrayList<>();

		assertEquals(3, makerNames.size());
		assertTrue(makerNames.contains("STAR"));
		assertTrue(makerNames.contains("TRABANT"));
		assertTrue(makerNames.contains("WARTBURG"));
	}

}
