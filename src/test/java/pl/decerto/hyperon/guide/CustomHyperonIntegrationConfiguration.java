package pl.decerto.hyperon.guide;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.core.HyperonEngineFactory;
import pl.decerto.hyperon.runtime.sql.DialectTemplate;

/**
 * This configuration should provide {@link HyperonEngine} in developer's mode, which can be configured with {@link HyperonEngineFactory}.
 * <br>Steps for creating Hyperon Engine:
 * <ol>
 *     <li>Create h2 dialect template</li>
 *     <li>Create data source with h2 driver</li>
 *     <li>Create Hyperon Engine using {@link HyperonEngineFactory}</li>
 * </ol>
 *
 * Hint: Developer can look up basic configuration for Hyperon Engine in {@link HyperonIntegrationConfiguration}.
 */
@Configuration
public class CustomHyperonIntegrationConfiguration {

	private final Environment env;
	private final boolean hyperonDevMode;
	private final String hyperonDevUser;

	@Autowired
	public CustomHyperonIntegrationConfiguration(Environment env, @Value("${hyperon.dev.mode:true}") boolean hyperonDevMode,
		@Value("${hyperon.dev.user:admin}") String hyperonDevUser) {

		this.env = env;
		this.hyperonDevMode = hyperonDevMode;
		this.hyperonDevUser = hyperonDevUser;
	}

	/**
	 * Create Hyperon Engine based on {@link HyperonEngineFactory}. Also make use of two methods to setup developer's mode:
	 * <ul>
	 *     <li>{@link HyperonEngineFactory#setDeveloperMode(boolean)} - activates developers mode</li>
	 *     <li>{@link HyperonEngineFactory#setUsername(String)} - provides username for user whose
	 *     session will be available for developer</li>
	 * </ul>
	 * If factory is setup properly create Hyperon Engine instance - {@link HyperonEngineFactory#create()}.
	 */
	@Bean
	HyperonEngine hyperonEngineFactory(HikariDataSource dataSource) {
		return null;
	}

	/**
	 * Create data source with h2 dialect.
	 */
	@Bean(destroyMethod = "close")
	HikariDataSource getHyperonDataSource(DialectTemplate dialectTemplate) {
		return null;
	}

	/**
	 * Create dialect template for h2.
	 */
	@Bean
	DialectTemplate h2Registry() {
		return null;
	}

}
