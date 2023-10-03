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
	
		request.setAttribute("id", System.currentTimeMillis());
		
		chain.doFilter(request, response);
	
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; 
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String ip = httpServletRequest.getRemoteAddr();
		String path = httpServletRequest.getRequestURL().toString();
		String method = httpServletRequest.getMethod();
		
		System.err.println("REQUEST: " + method + " " + ip + " " + path   + " RESPONSE: " + httpServletResponse.getStatus());
		
	}
	
	//               REQUEST                                        RESPUESTA
	// ID      TIMESTAMP   VERBO      IP       RECURSO      STATUS_CODE TIEMPO_RESPUESTA
	// 10      129344355    GET  10.250.2.3   /productos        200           34
	// 11      129344399    GET  10.250.2.5   /productos/45     404           17
	//
	
}
