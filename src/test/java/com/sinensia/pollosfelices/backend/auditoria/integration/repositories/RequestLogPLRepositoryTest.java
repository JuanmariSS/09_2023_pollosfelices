package com.sinensia.pollosfelices.backend.auditoria.integration.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.pollosfelices.backend.auditoria.integration.model.RequestLogPL;
import com.sinensia.pollosfelices.config.TestConfig;

@DataJpaTest
@Import(TestConfig.class)
@Sql(scripts={"/data/h2/schema_logs_test.sql","/data/h2/data_logs_test.sql"})
public class RequestLogPLRepositoryTest {

	@Autowired
	private RequestLogPLRepository requestLogPLRepository;

	@Autowired
	private SimpleDateFormat sdf;
	
	@Test
	void obtener_listado_request_logs_entre_fechas() throws Exception{
		
		Date desde = sdf.parse("2023-01-20 18:00:15");
		Date hasta = sdf.parse("2023-01-20 18:04:15");
		
		RequestLogPL requestLog1 = new RequestLogPL();
		RequestLogPL requestLog2 = new RequestLogPL();
		
		requestLog1.setId(102L);
		requestLog2.setId(103L);
		
		List<RequestLogPL> logsEsperados = Arrays.asList(requestLog1, requestLog2);
		
		List<RequestLogPL> logs = requestLogPLRepository.findByTimeStampBetweenOrderByTimeStampDesc(desde, hasta);
		
		assertEquals(2, logs.size());
		assertTrue(logs.containsAll(logsEsperados));
		assertEquals(requestLog2, logs.get(0));
		
	}
}
