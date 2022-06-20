package pl.decerto.hyperon.guide

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import pl.decerto.hyperon.runtime.core.HyperonEngine
import pl.decerto.hyperon.runtime.core.HyperonEngineFactory
import pl.decerto.hyperon.runtime.sql.DialectTemplate

/**
 * This configuration should provide [HyperonEngine] in developer's mode, which can be configured with [HyperonEngineFactory].
 * <br></br>Steps for creating Hyperon Engine:
 *
 *  1. Create h2 dialect template
 *  1. Create data source with h2 driver
 *  1. Create Hyperon Engine using [HyperonEngineFactory]
 *
 *
 * Hint: Developer can look up basic configuration for Hyperon Engine in [HyperonIntegrationConfiguration].
 */
@Configuration
open class CustomHyperonIntegrationKtConfiguration @Autowired constructor(
    private val env: Environment,
    @param:Value("\${hyperon.dev.mode:true}") private val hyperonDevMode: Boolean,
    @param:Value("\${hyperon.dev.user:admin}") private val hyperonDevUser: String
) {
    /**
     * Create Hyperon Engine based on [HyperonEngineFactory]. Also make use of two methods to setup developer's mode:
     *
     *  * [HyperonEngineFactory.setDeveloperMode] - activates developers mode
     *  * [HyperonEngineFactory.setUsername] - provides username for user whose
     * session will be available for developer
     *
     * If factory is setup properly create Hyperon Engine instance - [HyperonEngineFactory.create].
     */
    @Bean
    open fun hyperonEngineFactory(dataSource: HikariDataSource): HyperonEngine = TODO()

    /**
     * Create data source with h2 dialect.
     */
    @Bean(destroyMethod = "close")
    open fun getHyperonDataSource(dialectTemplate: DialectTemplate?): HikariDataSource = TODO()

    /**
     * Create dialect template for h2.
     */
    @Bean
    open fun h2Registry(): DialectTemplate = TODO()
}