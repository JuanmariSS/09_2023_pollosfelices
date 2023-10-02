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
		
		// TODO 1 sacar el nombre de la clase
		// TODO 2 sacar el nombre del método
		
		String nombreClase = "ClaseTal";
		String nombreMetodo = "hazEsto";
		
		logger.info("Invocado método {} de la clase {}", nombreMetodo, nombreClase);
		
		// TODO 4 Advanced! Mostrar los argumentos que se están pasando a los métodos...
	}
	
	// TODO 3 Interceptar ejecución de cualquier método de business para hacer lo mismo...
	
	
	
}
