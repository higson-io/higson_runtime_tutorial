package pl.decerto.hyperon.guide.intro

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import pl.decerto.hyperon.guide.HyperonIntegrationConfiguration
import pl.decerto.hyperon.runtime.core.HyperonContext
import pl.decerto.hyperon.runtime.core.HyperonEngine
import java.math.BigDecimal

@SpringBootTest(classes = [HyperonIntegrationConfiguration::class])
internal class ParameterKtTest {
    @Autowired
    private lateinit var hyperonEngine: HyperonEngine

    @Test
    fun `should get simple value from parameter`() {
        val ctx = HyperonContext()
            .with("coverage.code", "BI")
            .with("option.code", "BRONZE")
        val paramRows = hyperonEngine["demo.motor.coverage.availability", ctx]
        assertEquals(1, paramRows.size()) // one row only
        assertTrue(paramRows.boolean) // value is Boolean type
        assertTrue(paramRows.get<Boolean>()) // value is Boolean type
    }

    /**
     * <pre>
     * 1. Create context for parameter matrix, using HyperonContext.
     * 2. Get parameter called "demo.motor.coverage.bi.tariff".
     * 3. Compare results:
     * a) Check number of matched rows using size() on instance of ParamValue
     * b) Get value from ParamValue as BigDecimal using: "getBigDecimal()" and assign it to result1
     * c) Get value from ParamValue as BigDecimal using: "paramRow.get(BigDecimal.class)" and assign it to result2
    </pre> *
     */
    @Disabled("Remove this annotation when ready to run the test")
    @Test
    fun `todo should get simple value from parameter`() {
        val numberOfMatchedRows = TODO()
        val result1: BigDecimal = TODO()
        val result2: BigDecimal = TODO()
        assertEquals(1, numberOfMatchedRows) // one row only
        assertEquals(BigDecimal("0.507"), result1)
        assertEquals(BigDecimal("0.507"), result2)
    }
}
