package com.sinensia.pollosfelices.backend.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

	@Bean
	public SimpleDateFormat getSimpleDateFormat() {
		return new SimpleDateFormat("dd/MM/yyyy");
	}
	
}
