package com.galvao.vehicle.config.logging;

import com.galvao.vehicle.config.logging.interceptor.LogInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpLoggingConfiguration {
	@Bean
	public LogInterceptor loggingInterceptor() {
		return new LogInterceptor();
	}
}