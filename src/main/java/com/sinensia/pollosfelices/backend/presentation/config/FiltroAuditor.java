package com.sinensia.pollosfelices.backend.presentation.config;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroAuditor implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//Long startTime = System.nanoTime();
		
		Long id = System.currentTimeMillis();
		
		request.setAttribute("id", id);
		
		chain.doFilter(request, response);
	
		//Long elapsedTime = System.nanoTime() - startTime;
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; 
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String ip = httpServletRequest.getRemoteAddr();
		String path = httpServletRequest.getRequestURL().toString();
		String method = httpServletRequest.getMethod();
		
		Long entrada = (Long) request.getAttribute("id");
		Long tiempoTranscurrido = System.currentTimeMillis() - entrada;
		
		System.err.println("REQUEST: " + method + " " + ip + " " + path   + " RESPONSE: " + httpServletResponse.getStatus() + " " + tiempoTranscurrido);
		
	}
	
	//               REQUEST                                        RESPUESTA
	// ID      TIMESTAMP   VERBO      IP       RECURSO      STATUS_CODE TIEMPO_RESPUESTA
	// 10      129344355    GET  10.250.2.3   /productos        200           34
	// 11      129344399    GET  10.250.2.5   /productos/45     404           17
	//
	
}
