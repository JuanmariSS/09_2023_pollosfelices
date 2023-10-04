package com.sinensia.pollosfelices.config;

import java.text.SimpleDateFormat;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

	@Bean
	public SimpleDateFormat sdf() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
}
