package pl.decerto.hyperon.guide.parameters

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.smartparam.engine.core.output.ParamValue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.guide.parameters.SimpleTypesParameterKtTest.Gender.*
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * In these test cases you will learn more about simple types of parameter result, supported by Hyperon.
 * Result of each call to Hyperon Engine for parameters is of [ParamValue] type.<br></br>
 * Supported simple types are:
 *
 *  * String
 *  * Boolean
 *  * Integer/Long
 *  * BigDecimal
 *  * Date
 *  * Datetime
 *  * Enum
 *
 * More complex result types will be described in other test cases.
 *
 * There are multiple ways to access value from matrix. Most intuitive are methods with prefix "get"
 * followed by one of supported types, like: "getString()". Other possibilities will be presented in
 * test cases below.
 */
@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class SimpleTypesParameterKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine

    internal enum class Gender {
        FEMALE, MALE
    }

    @Test
    fun `should get simple value as boolean from parameter`() {
        val ctx = HyperonContext()
            .with("coverage.code", "BI")
            .with("option.code", "BRONZE")
        val paramRows: ParamValue = hyperonEngine["demo.motor.coverage.availability", ctx]
        assertEquals(1, paramRows.size())
        assertTrue(paramRows.boolean) // getBoolean() methods on matrix will get value from first row and first column
        assertTrue(paramRows.get<Boolean>()) // <T>get() methods on matrix will get value from first row and first column
        // assertTrue(paramRows.get(Boolean::class.java)) // TODO doesn't work in Kotlin get([class]) methods on matrix will get value from first row and first column
    }

    @Test
    fun `should get simple value as big decimal from parameter`() {
        val ctx = HyperonContext("quote.driver.gender", "F", "quote.driver.age", 48)
        val paramRows: ParamValue = hyperonEngine["demo.motor.coverage.bi.tariff", ctx]
        val expected = BigDecimal("0.656")
        assertEquals(1, paramRows.size())
        assertEquals(expected, paramRows.bigDecimal)
        assertEquals(expected, paramRows.get(BigDecimal::class.java))
        assertEquals(expected, paramRows.get<BigDecimal>())
    }

    @Test
    fun `should get simple value as integer from parameter`() {
        val ctx = HyperonContext("coverage.code", "PIP")
        val uniqueColumnCode = "position"
        val paramRows: ParamValue = hyperonEngine["demo.motor.coverage.position", ctx]
        val expected = 4
        assertEquals(1, paramRows.size())
        assertEquals(expected, paramRows.integer.toInt())
        assertEquals(expected, paramRows.getInteger(uniqueColumnCode).toInt())
        assertEquals(expected.toLong(), paramRows.holder.long.toLong())
        // assertEquals(expected, paramRow.<Integer>get().intValue());       // existing bug: [MPP-2492]
        // assertEquals(expected, paramRow.get(Integer.class).intValue());   // existing bug: [MPP-2492]
        // assertEquals(expected, paramRows.get(Long::class.java).toInt()) // workaround
        assertEquals(expected, paramRows.get<Long>().toInt()) // workaround
        assertEquals(expected, paramRows.get<Long>(uniqueColumnCode).toInt())
        assertEquals(expected.toLong(), paramRows.get<Long>().toLong())
    }

    @Test
    fun `should get simple value as string from parameters`() {
        val ctx = HyperonContext("coverage.code", "PIP")
        val uniqueColumnCode = "name"
        val paramRows: ParamValue = hyperonEngine["demo.motor.coverage.description", ctx]
        assertEquals(1, paramRows.size()) // one row only
        val expectedCodeName = "Personal Injury Protection"
        assertEquals(1, paramRows.size())
        assertEquals(expectedCodeName, paramRows.get(String::class.java))
        assertEquals(expectedCodeName, paramRows.get())
        assertEquals(expectedCodeName, paramRows.string)
        assertEquals(expectedCodeName, paramRows.get<String>())
        assertEquals(expectedCodeName, paramRows.get(uniqueColumnCode))
        assertEquals(expectedCodeName, paramRows.get(0))
        assertEquals(expectedCodeName, paramRows.get(0, String::class.java))
        assertEquals(expectedCodeName, paramRows.get(uniqueColumnCode, String::class.java))
    }

    @Test
    fun `should get simple value as date from parameters`() {
        val ctx = HyperonContext("quote.planCode", "BI")
        val uniqueColumnCode = "customDate"
        val paramRows: ParamValue = hyperonEngine["tutorial.date.example", ctx]
        val expectedDatetime = getDate(2019, 4, 2)
        assertEquals(1, paramRows.size())
        assertEquals(expectedDatetime, paramRows.date)
        assertEquals(expectedDatetime, paramRows.get())
        assertEquals(expectedDatetime, paramRows.get(Date::class.java))
        assertEquals(expectedDatetime, paramRows.get<Date>())
        assertEquals(expectedDatetime, paramRows.get(uniqueColumnCode))
        assertEquals(expectedDatetime, paramRows.get(0))
        assertEquals(expectedDatetime, paramRows.get(0, Date::class.java))
        assertEquals(expectedDatetime, paramRows.get(uniqueColumnCode, Date::class.java))
    }

    @Test
    fun `should get simple value as datetime from parameters`() {
        val ctx = HyperonContext("quote.planCode", "BI")
        val uniqueColumnCode = "customDatetime"
        val paramRows = hyperonEngine["tutorial.datetime.example", ctx]
        val expectedDatetime = getDateTime(2019, 4, 2, 14, 50, 55)
        assertEquals(1, paramRows.size())
        assertEquals(expectedDatetime, paramRows.datetime)
        assertEquals(expectedDatetime, paramRows.get())
        assertEquals(expectedDatetime, paramRows.get(Date::class.java))
        assertEquals(expectedDatetime, paramRows.get<Date>())
        assertEquals(expectedDatetime, paramRows.get(uniqueColumnCode))
        assertEquals(expectedDatetime, paramRows.get(0))
        assertEquals(expectedDatetime, paramRows.get(0, Date::class.java))
        assertEquals(expectedDatetime, paramRows.get(uniqueColumnCode, Date::class.java))
    }

    @Test
    fun `should get simple value as enum from parameters`() {
        val ctx = HyperonContext("quote.driver.gender", "F")
        val uniqueColumnCode = "value"
        val paramRows: ParamValue = hyperonEngine["tutorial.enum.example", ctx]
        assertEquals(1, paramRows.size())
        assertEquals(FEMALE, paramRows.getEnum(Gender::class.java))
        assertEquals(FEMALE, paramRows.getEnum(uniqueColumnCode, Gender::class.java))
    }

    @Test
    fun `should get all simple values from parameter`() {
        val ctx = HyperonContext().with("quote.planCode", "CASE1")
        val paramRows: ParamValue = hyperonEngine["tutorial.simple.types", ctx]
        val expectedDate = getDate(2019, 4, 2)
        val expectedDatetime = getDateTime(2019, 4, 8, 9, 22, 15)
        val row = paramRows.row()
        assertEquals(1, paramRows.size())
        assertEquals(expectedDate, row.getDate("mydate"))
        assertEquals(expectedDatetime, row.getDatetime("mydatetime"))
        assertEquals(BigDecimal("22.43"), row.getBigDecimal("mynumber"))
        assertEquals(32, row.getInteger("myinteger").toInt())
        assertFalse(paramRows.getBoolean("myboolean"))
    }

    /**
     * <pre>
     * 1. Create context for parameter matrix, using HyperonContext.
     * 2. Get parameter called "demo.motor.coverage.description".
     * 3. Compare results:
     * a) Check number of matched rows using size() on instance of ParamValue
     * b) Get value from ParamValue as String using: "paramRow.get(String.class)" and assign it to result1
     * c) Get value from ParamValue as String using: "paramRow.<String>get()" and assign it to result2
     * d) Get value from ParamValue as String using: "paramRow.getString()" and assign it to result3
     * e) Get value from ParamValue as String using: "paramRow.get()" and assign it to result4
    </String></pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get simple value as string from parameters`() {
        val matchedRows: Int = TODO()
        val result1: String = TODO()
        val result2: String = TODO()
        val result3: String = TODO()
        val result4: String = TODO()
        val expectedCodeName = "Personal Injury Protection"
        assertEquals(1, matchedRows)
        assertEquals(expectedCodeName, result1)
        assertEquals(expectedCodeName, result2)
        assertEquals(expectedCodeName, result3)
        assertEquals(expectedCodeName, result4)
    }

    /**
     * This test case checks from parameter: "tutorial.simple.types" all supported types (except String).
     * 1. Create proper context for case 2
     * 2. Get parameter called: "tutorial.simple.types"
     * 3. Compare results for each type, either by column name or index.
     */
//    @Disabled("Remove this annotation when ready to run the test")
    @Test
    @Disabled("Remove this annotation when ready to run the test")
    fun `todo should get all simple values from parameter`() {
        val matchedRows: Int = TODO()
        val result1: Date = TODO()
        val result2: Date = TODO()
        val result3: BigDecimal = TODO()
        val result4: Int = TODO()
        val result5: Boolean = TODO()
        val expectedDate = getDate(2019, 4, 8)
        val expectedDatetime = getDateTime(2019, 4, 8, 10, 24, 56)
        assertEquals(1, matchedRows)
        assertEquals(expectedDate, result1)
        assertEquals(expectedDatetime, result2)
        assertEquals(BigDecimal("123.4"), result3)
        assertEquals(15, result4)
        assertTrue(result5)
    }

    /**
     * <pre>
     * 1. Create context for parameter matrix, using HyperonContext.
     * 2. Get parameter called "tutorial.enum.example".
     * 3. Compare results:
     * a) Check number of matched rows using size() on instance of ParamValue
     * b) Get value from ParamValue as Enum using: "paramRow.getEnum(Gender.class)" and assign it to result1
     * c) Get value from ParamValue as Enum using: "paramRow.getEnum(uniqueCode, Gender.class)" and assign it to result2
    </pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get simple value as enum from parameters`() {
        val matchedRows: Int = TODO()
        val result1: Gender = TODO()
        val result2: Gender = TODO()
        assertEquals(1, matchedRows)
        assertEquals(MALE, result1)
        assertEquals(MALE, result2)
    }

    private fun getDateTime(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int, second: Int): Date {
        val ofDatetime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second)
        return Date.from(ofDatetime.atZone(ZoneId.systemDefault()).toInstant())
    }

    private fun getDate(year: Int, month: Int, dayOfMonth: Int): Date {
        val ofDate = LocalDate.of(year, month, dayOfMonth)
        return Date.from(ofDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }
}