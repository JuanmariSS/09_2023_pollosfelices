package com.sinensia.pollosfelices.backend.presentation.api.restcontrollers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinensia.pollosfelices.backend.business.model.DatosContacto;
import com.sinensia.pollosfelices.backend.business.model.Direccion;
import com.sinensia.pollosfelices.backend.business.model.Establecimiento;
import com.sinensia.pollosfelices.backend.business.services.EstablecimientoServices;
import com.sinensia.pollosfelices.backend.presentation.config.FiltroAuditor;
import com.sinensia.pollosfelices.backend.presentation.config.RespuestaErrorHttp;

@WebMvcTest(controllers=EstablecimientoController.class,excludeFilters=@ComponentScan.Filter(classes=FiltroAuditor.class, 
type=FilterType.ASSIGNABLE_TYPE))
public class EstablecimientoControllerTest {

	@Autowired
	private MockMvc miniPostman;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private EstablecimientoServices establecimientoServices;
	
	private Establecimiento establecimiento1;
	private Establecimiento establecimiento2;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void pedimos_todos_los_establecimientos() throws Exception {
		
		List<Establecimiento> establecimientos = Arrays.asList(establecimiento1, establecimiento2);
		when(establecimientoServices.getAll()).thenReturn(establecimientos);
	
		MvcResult respuesta = miniPostman.perform(get("/establecimientos").contentType("application/json"))
									.andExpect(status().isOk())
									.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String clientesJSON = objectMapper.writeValueAsString(establecimientos);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(clientesJSON);
		 
	}
	
	@Test
	void pedimos_establecimiento_por_codigo_existente() throws Exception {
		
		when(establecimientoServices.read(100L)).thenReturn(Optional.of(establecimiento1));
		
		MvcResult respuesta = miniPostman.perform(get("/establecimientos/100").contentType("application/json"))
								.andExpect(status().isOk())
								.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String cliente1JSON = objectMapper.writeValueAsString(establecimiento1);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(cliente1JSON);
		
	}
	
	@Test
	void pedimos_establecimiento_por_codigo_NO_existente() throws Exception {
		
		when(establecimientoServices.read(100L)).thenReturn(Optional.empty());
		
		MvcResult respuesta = miniPostman.perform(get("/establecimientos/100").contentType("application/json"))
								.andExpect(status().isNotFound())
								.andReturn();
		
		RespuestaErrorHttp respuestaErrorHttp = new RespuestaErrorHttp("No existe el establecimiento 100");
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String respuestaErrorHttpJSON = objectMapper.writeValueAsString(respuestaErrorHttp);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(respuestaErrorHttpJSON);
		
	}
	
	@Test
	void creamos_establecimiento_ok() throws Exception {
	
		Establecimiento nuevoEstablecimiento = new Establecimiento();
		
		when(establecimientoServices.create(nuevoEstablecimiento)).thenReturn(10000L);
		
		String requestBody = objectMapper.writeValueAsString(nuevoEstablecimiento);
		
		miniPostman.perform(post("/establecimientos").content(requestBody).contentType("application/json"))
								.andExpect(status().isCreated())
								.andExpect(header().string("Location","http://localhost/establecimientos/10000"));
		
	}
	
	@Test
	void creamos_establecimiento_con_codigo_NO_null() throws Exception {
		
		when(establecimientoServices.create(establecimiento1)).thenThrow(new IllegalStateException("Problema con el codigo..."));
		
		String requestBody = objectMapper.writeValueAsString(establecimiento1);
		
		MvcResult respuesta = miniPostman.perform(post("/establecimientos").content(requestBody).contentType("application/json"))
								.andExpect(status().isBadRequest())
								.andReturn();
		
		RespuestaErrorHttp respuestaErrorHttp = new RespuestaErrorHttp("Problema con el codigo...");
		
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
		
		establecimiento1 = new Establecimiento();
		establecimiento2 = new Establecimiento();
		
		establecimiento1.setCodigo(100L);
		establecimiento2.setCodigo(101L);
		
		establecimiento1.setNombreComercial("Nombre Establecimiento 1");
		establecimiento2.setNombreComercial("Nombre Establecimiento 2");
		
		Direccion direccion1 = new Direccion();
		DatosContacto datosContacto1 = new DatosContacto();
		
		Direccion direccion2 = new Direccion();
		DatosContacto datosContacto2 = new DatosContacto();
		
		direccion1.setDireccion("Avda. Del Pino, 23");
		direccion1.setCodigoPostal("08020");
		direccion1.setPoblacion("Barcelona");
		direccion1.setProvincia("Barcelona");
		direccion1.setPais("España");
		
		direccion2.setDireccion("Avda. Del Roble, 14");
		direccion2.setCodigoPostal("08026");
		direccion2.setPoblacion("Badalona");
		direccion2.setProvincia("Barcelona");
		direccion2.setPais("España");
		
		datosContacto1.setEmail("email1@gmail.com");
		datosContacto2.setEmail("email2@gmail.com");
		
		establecimiento1.setDireccion(direccion1);
		establecimiento1.setDatosContacto(datosContacto1);
		
		establecimiento2.setDireccion(direccion2);
		establecimiento2.setDatosContacto(datosContacto2);
		
	}
}
