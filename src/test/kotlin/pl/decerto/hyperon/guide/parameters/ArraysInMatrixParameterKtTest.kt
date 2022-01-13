package pl.decerto.hyperon.guide.parameters

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.smartparam.engine.core.output.ParamValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine

/**
 * In this test cases you will learn about a little more complex values in matrix - arrays.
 * Arrays can hold multiple values of types supported by Hyperon in
 * one matrix cell. Access to this functionality can be achieved using:
 * `row().getStringArray(..) or row().getArray(..)` on [ParamValue]
 * instance. Accessing cell that holds arrays can be done as for "simple" types -
 * by index or unique column name.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class ArraysInMatrixParameterKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine

    @Test
    fun `should get array of string from parameter`() {
        val ctx = HyperonContext("coverage.code", "BI")
        val paramValue = hyperonEngine["tutorial.array.example", ctx]
        val expectedResultColumn1 = arrayOf("BRONZE", "GOLD", "SILVER")
        val expectedResultColumn2 = arrayOf(1, 2, 3)
        assertEquals(1, paramValue.size())

        /* First column **/
        val firstColumnIndex = 0
        assertArrayEquals(expectedResultColumn1, paramValue.row().getStringArray(firstColumnIndex))
        assertArrayEquals(expectedResultColumn1, paramValue.row().getStringArray("optionCodes"))
        assertEquals("BRONZE", paramValue.row().getArray(firstColumnIndex)[0].value)
        assertEquals("BRONZE", paramValue.row().getArray(firstColumnIndex)[0].string)
        assertEquals("GOLD", paramValue.row().getArray(firstColumnIndex)[1].value)
        assertEquals("GOLD", paramValue.row().getArray(firstColumnIndex)[1].string)
        assertEquals("SILVER", paramValue.row().getArray(firstColumnIndex)[2].value)
        assertEquals("SILVER", paramValue.row().getArray(firstColumnIndex)[2].string)

        /* Second column **/
        val secondColumnIndex = 1
        assertArrayEquals(expectedResultColumn2, paramValue.row().getIntegerArray(1))
        assertArrayEquals(expectedResultColumn2, paramValue.row().getIntegerArray("optionOrder"))
        assertEquals(1L, paramValue.row().getArray(secondColumnIndex)[0].value)
        assertEquals(1, paramValue.row().getArray(secondColumnIndex)[0].integer.toInt())
        assertEquals(2L, paramValue.row().getArray(secondColumnIndex)[1].value)
        assertEquals(2, paramValue.row().getArray(secondColumnIndex)[1].integer.toInt())
        assertEquals(3L, paramValue.row().getArray(secondColumnIndex)[2].value)
        assertEquals(3, paramValue.row().getArray(secondColumnIndex)[2].integer.toInt())
    }

    /**
     * <pre>
     * 1. Create context for parameter matrix, using HyperonContext.
     * 2. Get parameter called "tutorial.array.example".
     * 3. Compare results:
     * a) Check number of matched rows using size() on instance of ParamValue
     * b) Get value from ParamValue as String[] using: "paramValue.row().getStringArray(index)" and assign it to result1
     * c) Get value from ParamValue as String[] using: "paramValue.row().getStringArray(uniqueCode)" and assign it to result2
     * d) Get value from ParamValue as String using: "paramValue.row().getArray(column index)[array index].getString()" and assign it to result3
     * 4. Verify values in the second column of the parameter
    </pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get array of string from parameter`() {
        val expectedArray1 = arrayOf("GOLD", "SILVER")
        val expectedArray2 = arrayOf(1, 2)
        val matchedRows: Int = TODO()
        /* Verify first column of the parameter */
        val result1: Array<String> = TODO()
        val result2: Array<String> = TODO()
        val result3: String = TODO()
        assertEquals(1, matchedRows)
        assertArrayEquals(expectedArray1, result1)
        assertArrayEquals(expectedArray1, result2)
        assertEquals("SILVER", result3)
        /* Verify second column of the parameter */
        val result4: Array<Int> = TODO()
        val result5: Array<Int> = TODO()
        val result6: Int = TODO()
        assertArrayEquals(expectedArray2, result4)
        assertArrayEquals(expectedArray2, result5)
        assertEquals(2, result6)
    }
}