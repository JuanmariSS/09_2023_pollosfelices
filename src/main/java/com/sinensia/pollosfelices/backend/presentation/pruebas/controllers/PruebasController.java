package com.sinensia.pollosfelices.backend.presentation.pruebas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosfelices.backend.business.services.ProductoServices;

@RestController
@RequestMapping("/pruebas")
public class PruebasController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping("/trigger1")
	public Object disparador1() {
		return productoServices.getBetweenPriceRange(10.0, 15.00);
	}
	
	
}
