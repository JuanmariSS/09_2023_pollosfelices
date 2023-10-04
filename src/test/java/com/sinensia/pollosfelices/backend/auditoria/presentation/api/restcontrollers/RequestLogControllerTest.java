package com.sinensia.pollosfelices.backend.auditoria.presentation.api.restcontrollers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinensia.pollosfelices.backend.auditoria.business.model.RequestLog;
import com.sinensia.pollosfelices.backend.auditoria.business.services.RequestLogServices;
import com.sinensia.pollosfelices.backend.presentation.config.RespuestaErrorHttp;
import com.sinensia.pollosfelices.config.TestConfig;

@WebMvcTest(controllers=RequestLogController.class)
@Import(TestConfig.class)
public class RequestLogControllerTest {

	@Autowired
	private MockMvc miniPostman;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private RequestLogServices requestLogServices;
	
	@Autowired
	private SimpleDateFormat sdf;
	
	private RequestLog requestLog1;
	private RequestLog requestLog2;

	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void pedimos_todos_los_request_logs() throws Exception {
		
		List<RequestLog> logs = Arrays.asList(requestLog1, requestLog2);
		when(requestLogServices.getAll()).thenReturn(logs);
	
		MvcResult respuesta = miniPostman.perform(get("/request-logs").contentType("application/json"))
									.andExpect(status().isOk())
									.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String clientesJSON = objectMapper.writeValueAsString(logs);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(clientesJSON);
		 
	}
	
	@Test
	void pedimos_request_logs_entre_fechas() throws Exception {
				
		String strDesde = "2023-01-20 10:00:00";
		String strHasta = "2023-01-20 11:10:00";
		
		Date desde = sdf.parse(strDesde);
		Date hasta = sdf.parse(strHasta);
		
		List<RequestLog> logs = Arrays.asList(requestLog1, requestLog2);
		when(requestLogServices.getBetweenDates(desde, hasta)).thenReturn(logs);
	
		MvcResult respuesta = miniPostman.perform(get("/request-logs?desde=2023-01-20 10:00:00&hasta=2023-01-20 11:10:00").contentType("application/json"))
									.andExpect(status().isOk())
									.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String clientesJSON = objectMapper.writeValueAsString(logs);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(clientesJSON);
		 
	}
	
	@Test
	void pedimos_request_log_por_id_existente() throws Exception {
		
		when(requestLogServices.read(1000L)).thenReturn(Optional.of(requestLog1));
		
		MvcResult respuesta = miniPostman.perform(get("/request-logs/1000").contentType("application/json"))
								.andExpect(status().isOk())
								.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String requestLogJSON = objectMapper.writeValueAsString(requestLog1);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(requestLogJSON);
		
	}
	
	@Test
	void pedimos_request_log_por_id_NO_existente() throws Exception {
		
		when(requestLogServices.read(100L)).thenReturn(Optional.empty());
		
		MvcResult respuesta = miniPostman.perform(get("/request-logs/100").contentType("application/json"))
								.andExpect(status().isNotFound())
								.andReturn();
		
		RespuestaErrorHttp respuestaErrorHttp = new RespuestaErrorHttp("No existe el log con id 100");
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String respuestaErrorHttpJSON = objectMapper.writeValueAsString(respuestaErrorHttp);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaErrorHttpJSON);
		
	}
	
	// *********************************************************
	//
	// Private Methods
	//
	// *********************************************************
	
	private void initObjects() {
		
		requestLog1 = new RequestLog();
		requestLog1.setId(1000L);
		requestLog1.setContentType("application/json");
		requestLog1.setTimeStamp(new Date(10000000L));
		requestLog1.setMethod("GET");
		requestLog1.setIp("10.230.23.22");
		requestLog1.setElapsedTime(123L);
		requestLog1.setStatusCode(200);
		
		requestLog2 = new RequestLog();
		requestLog2.setId(1001L);
		requestLog2.setContentType("application/json");
		requestLog2.setTimeStamp(new Date(10000100L));
		requestLog2.setMethod("POST");
		requestLog2.setIp("10.230.23.67");
		requestLog2.setElapsedTime(33L);
		requestLog2.setStatusCode(201);	
	}
}
