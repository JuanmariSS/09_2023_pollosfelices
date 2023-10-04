package com.sinensia.pollosfelices.backend.presentation.config;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinensia.pollosfelices.backend.auditoria.business.model.RequestLog;
import com.sinensia.pollosfelices.backend.auditoria.business.services.RequestLogServices;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Component
public class FiltroAuditor implements Filter {

	@Autowired
	private RequestLogServices requestLogServices;
	
	@Override
	@Transactional
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		Date entrada = new Date();
		
		chain.doFilter(request, response);
	
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; 
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		RequestLog requestLog = new RequestLog();
		requestLog.setTimeStamp(entrada);
		requestLog.setIp(httpServletRequest.getRemoteAddr());
		requestLog.setResource(httpServletRequest.getRequestURL().toString());
		requestLog.setMethod(httpServletRequest.getMethod());
		
		requestLog.setStatusCode(httpServletResponse.getStatus());
		requestLog.setContentType(httpServletResponse.getHeader("Content-Type"));
		requestLog.setElapsedTime(System.currentTimeMillis() - entrada.getTime());
		
		requestLogServices.create(requestLog);
		
	}
	
}
