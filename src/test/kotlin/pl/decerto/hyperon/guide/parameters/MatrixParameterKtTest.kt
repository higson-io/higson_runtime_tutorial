package pl.decerto.hyperon.guide.parameters

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.smartparam.engine.core.output.ParamValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine

/**
 * In this section you will learn more about complex results of parameters matrix.
 * Sometimes parameters instead of multiple values in "one row", return multiple column/rows -
 * a matrix. This matrix is represented in Hyperon as an instance of [ParamValue].
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class MatrixParameterKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine

    @Test
    fun `should get matrix from parameter`() {
        val ctx = HyperonContext("quote.vehicle.productionYear", "1970")
        val matrix = hyperonEngine["demo.motor.dict.vehicle.availableMakes", ctx]
        assertEquals(2, matrix.size())

        // using get on ParamValue object will always return data from the first row of matrix
        assertEquals(
            "STAR",
            matrix.get(String::class.java)
        ) // get() method on matrix will get value from first row and first column
        assertEquals("STAR", matrix.get(0, String::class.java))
        assertEquals("STAR", matrix.getString("make"))
        assertEquals(722, matrix.get<Long>(1).toLong())
        assertEquals(722, matrix.getInteger("make_id").toInt())
        val firstRow = matrix.row()
        assertEquals("STAR", firstRow.getString("make")) // first row with column code "make"
        assertEquals("STAR", matrix.get(0, 0, String::class.java)) // first column of first row
        assertEquals(722, firstRow.getInteger("make_id").toInt()) // first row with column code "make"
        assertEquals(722, matrix.get<Long>(0, 1).toInt()) // second column of first row
        val secondRow = matrix.row(1)
        assertEquals("WARTBURG", secondRow.getString("make")) // second row with column code "make"
        assertEquals("WARTBURG", matrix.get(1, 0, String::class.java)) // first column of second row
        assertEquals(230, secondRow.getInteger("make_id").toInt()) // second row with column code "make_id"
        assertEquals(230, matrix.get<Long>(1, 1).toInt()) // second column of first row
    }

    @Test
    fun `should get matrix using iterator from parameter`() {
        val ctx = HyperonContext("quote.vehicle.productionYear", "1970")
        val matrix = hyperonEngine["demo.motor.dict.vehicle.availableMakes", ctx]
        // this will iterate over each row and get value from second column as String
        val makerIds = matrix.map { it.getInteger(1) }
        assertEquals(2, matrix.size())
        assertEquals(2, makerIds.size)
        assertTrue(722 in makerIds)
        assertTrue(230 in makerIds)
    }

    /**
     * <pre>
     * 1. Create context for parameter matrix, using HyperonContext, that will match assertions of this test.
     * 2. Get parameter called "demo.motor.dict.vehicle.availableMakes".
     * 3. Compare results for each row/column result.
     * hint: use Tester in studio for this parameter
    </pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get matrix from parameter`() {
        val numberOfMatchedRows: Int = TODO()
        assertEquals(3, numberOfMatchedRows)
        val entry_0_0: String = TODO()
        val entry_0_1: Long = TODO()
        val entry_1_0: String = TODO()
        val entry_1_1: Long = TODO()
        val entry_2_0: String = TODO()
        val entry_2_1: Long = TODO()
        assertEquals("STAR", entry_0_0)
        assertEquals(722, entry_0_1)
        assertEquals("TRABANT", entry_1_0)
        assertEquals(221, entry_1_1)
        assertEquals("WARTBURG", entry_2_0)
        assertEquals(230, entry_2_1)
    }

    /**
     * <pre>
     * 1. Create context for parameter matrix, using HyperonContext, that will match assertions of this test.
     * 2. Get parameter called "demo.motor.dict.vehicle.availableMakes".
     * 3. Build list of values from unique column name "make" (first column)
    </pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get matrix using iterator from parameter`() {
        val makerNames: List<String> = TODO()
        assertEquals(3, makerNames.size)
        assertTrue("STAR" in makerNames)
        assertTrue("TRABANT" in makerNames)
        assertTrue("WARTBURG" in makerNames)
    }
}