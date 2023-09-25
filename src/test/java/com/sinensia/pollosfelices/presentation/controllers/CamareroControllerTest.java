package com.sinensia.pollosfelices.presentation.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinensia.pollosfelices.business.model.Camarero;
import com.sinensia.pollosfelices.business.model.DatosContacto;
import com.sinensia.pollosfelices.business.model.Direccion;
import com.sinensia.pollosfelices.business.services.CamareroServices;
import com.sinensia.pollosfelices.presentation.config.RespuestaErrorHttp;

@WebMvcTest(controllers=CamareroController.class)
public class CamareroControllerTest {

	@Autowired
	private MockMvc miniPostman;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CamareroServices camareroServices;
	
	private Camarero camarero1;
	private Camarero camarero2;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void creamos_camarero_ok() throws Exception {
		
		Camarero nuevoCamarero = new Camarero();
		
		when(camareroServices.create(nuevoCamarero)).thenReturn(10000L);
		
		String requestBody = objectMapper.writeValueAsString(nuevoCamarero);
		
		miniPostman.perform(post("/camareros").content(requestBody).contentType("application/json"))
								.andExpect(status().isCreated())
								.andExpect(header().string("Location","http://localhost/camareros/10000"));
		
	}
	
	@Test
	void creamos_camarero_con_id_NO_null() throws Exception {
		
		when(camareroServices.create(camarero1)).thenThrow(new IllegalStateException("Problema con el id..."));
		
		String requestBody = objectMapper.writeValueAsString(camarero1);
		
		MvcResult respuesta = miniPostman.perform(post("/camareros").content(requestBody).contentType("application/json"))
								.andExpect(status().isBadRequest())
								.andReturn();
		
		RespuestaErrorHttp respuestaErrorHttp = new RespuestaErrorHttp("Problema con el id...");
		
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
		
		Direccion direccion1 = new Direccion();
		Direccion direccion2 = new Direccion();
		
		DatosContacto datosContacto1 = new DatosContacto();
		DatosContacto datosContacto2 = new DatosContacto();
		
		direccion1.setDireccion("Avda. De los Pinos, 234 ático 2");
		direccion1.setPoblacion("Santa Perpetua de Mogoda (Polígono Industrial URBINSA");
		direccion1.setCodigoPostal("08034");
		direccion1.setProvincia("Barcelona");
		direccion1.setPais("España");
		
		direccion2.setDireccion("c/ San Blas, 23");
		direccion2.setPoblacion("Badalona");
		direccion2.setCodigoPostal("08020");
		direccion2.setProvincia("Barcelona");
		direccion2.setPais("España");
		
		datosContacto1.setTelefono("93 2309082");
		datosContacto1.setEmail("genaro23@hotmail.com");
		
		datosContacto2.setEmail("honorio@gmail.com");
		
		camarero1 = new Camarero();
		camarero2 = new Camarero();
		
		camarero1.setId(100L);
		camarero1.setNombre("Genaro");
		camarero1.setApellido1("Ruiz");
		camarero1.setApellido2("Merino");
		camarero1.setLicenciaManipuladorAlimentos("L33355");
		camarero1.setDireccion(direccion1);
		camarero1.setDatosContacto(datosContacto1);
		
		camarero2.setId(101L);
		camarero2.setNombre("Anna");
		camarero2.setApellido1("López");
		camarero2.setApellido2("Rodillo");
		camarero2.setLicenciaManipuladorAlimentos("L34425");
		camarero2.setDireccion(direccion2);
		camarero2.setDatosContacto(datosContacto2);
		
	}
	
}
