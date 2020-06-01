package com.galvao.vehicle.config;

import org.junit.ClassRule;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
@Profile("TEST")
public class DataSourceConfiguration {
	public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@ClassRule
		PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer();

		@Override
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			this.postgreSQLContainer.start();
			TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
					configurableApplicationContext,
					"spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
					"spring.datasource.username=" + postgreSQLContainer.getUsername(),
					"spring.datasource.password=" + postgreSQLContainer.getPassword()
			);
		}
	}

}