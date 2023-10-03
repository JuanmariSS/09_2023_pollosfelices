package com.sinensia.pollosfelices.backend.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LogAspectConfig {

	private Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);
	
	@Before(value="execution(* com.sinensia.pollosfelices.backend.presentation.controllers.*.*(..))")
	public void logPresentationLayer(JoinPoint joinPoint) {
		
		String nombreClase = joinPoint.getTarget().getClass().getSimpleName();
		String nombreMetodo = joinPoint.getSignature().getName();
	
		logger.info("Invocado método({}) de la clase {}", nombreMetodo, nombreClase);
		
	}
	
	@Before(value="execution(* com.sinensia.pollosfelices.backend.business.services.impl.*.*(..))")
	public void logBusinessLayer(JoinPoint joinPoint) {
		
		String nombreClase = joinPoint.getTarget().getClass().getSimpleName();
		String nombreMetodo = joinPoint.getSignature().getName();
	
		logger.info("Invocado método({}) de la clase {}", nombreMetodo, nombreClase);
		
	}

}
