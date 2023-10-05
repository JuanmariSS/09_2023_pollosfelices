package com.sinensia.pollosfelices.backend.auditoria.business.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sinensia.pollosfelices.backend.auditoria.business.model.RequestLog;
import com.sinensia.pollosfelices.backend.auditoria.integration.model.RequestLogPL;
import com.sinensia.pollosfelices.backend.auditoria.integration.repositories.RequestLogPLRepository;

@ExtendWith(MockitoExtension.class)
class RequestLogServicesImplTest {

	@Mock
	private RequestLogPLRepository requestLogPLRepository;
	
	@Mock
	private DozerBeanMapper mapper;
	
	@InjectMocks
	private RequestLogServicesImpl requestLogServicesImpl;
	
	@Test
	void creamos_log_ok() {
		
		RequestLog requestLog = new RequestLog();
		requestLog.setId(null);
		
		RequestLogPL requestLogPL = new RequestLogPL();
		RequestLogPL createdRequestLogPL = new RequestLogPL();
		createdRequestLogPL.setId(25000L);
		
		when(mapper.map(requestLog, RequestLogPL.class)).thenReturn(requestLogPL);
		when(requestLogPLRepository.save(requestLogPL)).thenReturn(createdRequestLogPL);

		Long id = requestLogServicesImpl.create(requestLog);
		
		assertEquals(25000L, id);
		
	}
	
	@Test
	@Disabled
	void testRead() {
		// TODO
	}

	@Test
	@Disabled
	void testGetAll() {
		// TODO
	}

	@Test
	@Disabled
	void testGetBetweenDates() {
		// TODO
	}

}
