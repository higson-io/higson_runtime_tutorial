package pl.decerto.hyperon.guide;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import pl.decerto.hyperon.runtime.core.HyperonEngine;
import pl.decerto.hyperon.runtime.core.HyperonEngineFactory;
import pl.decerto.hyperon.runtime.sql.DialectRegistry;
import pl.decerto.hyperon.runtime.sql.DialectTemplate;

@Configuration
public class HyperonIntegrationConfiguration {

	private final Environment env;

	@Autowired
	public HyperonIntegrationConfiguration(Environment env) {
		this.env = env;
	}

	/**
	 * Creates Hyperon Engine connected to given {@code dataSource} based on factory.
	 *
	 * @param dataSource for engine
	 * @return ready to use hyperon engine
	 */
	@Bean
	HyperonEngine hyperonEngineFactory(HikariDataSource dataSource) {
		HyperonEngineFactory hyperonEngineFactory = new HyperonEngineFactory();
		hyperonEngineFactory.setDataSource(dataSource);
		return hyperonEngineFactory.create();
	}

	/**
	 * Creates data source for dialect template.
	 * @param dialectTemplate used for fetching driver class name
	 * @return data source
	 */
	@Bean(destroyMethod = "close")
	HikariDataSource getHyperonDataSource(DialectTemplate dialectTemplate) {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setUsername(env.getProperty("hyperon.database.username"));
		dataSource.setPassword(env.getProperty("hyperon.database.password"));
		dataSource.setJdbcUrl(env.getProperty("hyperon.database.url"));
		dataSource.setDriverClassName(dialectTemplate.getJdbcDriverClassName());
		return dataSource;
	}

	/**
	 * Creates dialect template for {@code hyperon.database.dialect} based on registry. In this tutorial it will be h2.
	 * @return dialect template
	 */
	@Bean
	DialectTemplate h2Registry() {
		DialectRegistry registry = new DialectRegistry();
		registry.setDialect(env.getProperty("hyperon.database.dialect"));
		return registry.create();
	}

}
