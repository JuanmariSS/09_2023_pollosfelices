package com.sinensia.pollosfelices.backend.presentation.api.restcontrollers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sinensia.pollosfelices.backend.business.model.Producto;
import com.sinensia.pollosfelices.backend.business.services.CategoriaServices;
import com.sinensia.pollosfelices.backend.business.services.ProductoServices;

@WebMvcTest(controllers=ProductoController.class)
public class ProductoControllerTest {
	
	@Autowired
	private MockMvc miniPostman;
	
	@MockBean
	private ProductoServices productoServices;
	
	@MockBean
	private CategoriaServices categoriaServices;
	
	@Test
	void eliminamos_producto() throws Exception {
		
		Producto producto = new Producto();
		producto.setCodigo(100L);
		
		when(productoServices.read(100L)).thenReturn(Optional.of(producto));
		
		miniPostman.perform(delete("/productos/100").contentType("application/json"))
		.andExpect(status().isNoContent());
		
		verify(productoServices, times(1)).delete(100L);
	}

}
