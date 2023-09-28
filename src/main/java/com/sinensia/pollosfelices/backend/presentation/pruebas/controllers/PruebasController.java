package com.sinensia.pollosfelices.backend.presentation.pruebas.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosfelices.backend.business.model.Producto;
import com.sinensia.pollosfelices.backend.business.services.ProductoServices;

@RestController
@RequestMapping("/pruebas")
public class PruebasController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/trigger1")
	public Object disparador1() {
		return productoServices.getBetweenPriceRange(6.0, 9.00);
	}
	
	@GetMapping("/trigger2")
	public Object disparador2() {
		return productoServices.getBetweenPriceRange(6.0, 9.00);
	}
	
	@GetMapping("/trigger3")
	@Transactional
	public Object disparador3() {
		
		Producto producto1 = new Producto();
		Producto producto2 = new Producto();
		
		producto1.setCodigo(100L);
		producto2.setCodigo(120L);
		
		List<Producto> productos = Arrays.asList(producto1, producto2);
		
		productoServices.incrementarPrecio(productos, 666.0);
		
		return "ok";
	}
	
	@GetMapping("/trigger4")
	@Transactional
	public Object disparador4() {
		return productoServices.getEstadisticaNumeroProductosByCategoria();
	}
	
	@GetMapping("/trigger5")
	@Transactional
	public Object disparador5() {
		return productoServices.getEstadisticaPrecioMedioProductosByCategoria();
	}
}
