package com.sinensia.pollosfelices.presentation.controllers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinensia.pollosfelices.backend.business.model.Cliente;
import com.sinensia.pollosfelices.backend.business.model.DatosContacto;
import com.sinensia.pollosfelices.backend.business.model.Direccion;
import com.sinensia.pollosfelices.backend.business.services.ClienteServices;
import com.sinensia.pollosfelices.backend.presentation.config.RespuestaErrorHttp;
import com.sinensia.pollosfelices.backend.presentation.controllers.ClienteController;

@WebMvcTest(controllers=ClienteController.class)
public class ClienteControllerTest {

	@Autowired
	private MockMvc miniPostman;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ClienteServices clienteServices;
	
	private Cliente cliente1;
	private Cliente cliente2;
	
	@BeforeEach
	void init() {
		initObjects();
	}
	
	@Test
	void pedimos_todos_los_clientes() throws Exception {
		
		List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
		when(clienteServices.getAll()).thenReturn(clientes);
	
		MvcResult respuesta = miniPostman.perform(get("/clientes").contentType("application/json"))
									.andExpect(status().isOk())
									.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String clientesJSON = objectMapper.writeValueAsString(clientes);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(clientesJSON);
		 
	}
	
	@Test
	void pedimos_cliente_por_codigo_existente() throws Exception {
		
		when(clienteServices.read(100L)).thenReturn(Optional.of(cliente1));
		
		MvcResult respuesta = miniPostman.perform(get("/clientes/100").contentType("application/json"))
								.andExpect(status().isOk())
								.andReturn();
		
		String responseBody = respuesta.getResponse().getContentAsString(StandardCharsets.UTF_8);
		String cliente1JSON = objectMapper.writeValueAsString(cliente1);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(cliente1JSON);
		
	}
	
	@Test
	void pedimos_cliente_por_codigo_NO_existente() throws Exception {
		
		when(clienteServices.read(100L)).thenReturn(Optional.empty());
		
		MvcResult respuesta = miniPostman.perform(get("/clientes/100").contentType("application/json"))
								.andExpect(status().isNotFound())
								.andReturn();
		
		RespuestaErrorHttp respuestaErrorHttp = new RespuestaErrorHttp("Ooops... No existe el cliente 100");
		
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
		
		cliente1 = new Cliente();
		cliente2 = new Cliente();
		
		cliente1.setId(100L);
		cliente1.setNombre("Genaro");
		cliente1.setApellido1("Ruiz");
		cliente1.setApellido2("Merino");
		cliente1.setTarjetaGold(true);
		cliente1.setDireccion(direccion1);
		cliente1.setDatosContacto(datosContacto1);
		
		cliente2.setId(101L);
		cliente2.setNombre("Anna");
		cliente2.setApellido1("López");
		cliente2.setApellido2("Rodillo");
		cliente2.setTarjetaGold(false);
		cliente2.setDireccion(direccion2);
		cliente2.setDatosContacto(datosContacto2);
		
	}
}
