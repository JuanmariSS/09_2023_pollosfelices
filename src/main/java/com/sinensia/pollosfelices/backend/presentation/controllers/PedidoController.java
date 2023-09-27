package com.sinensia.pollosfelices.backend.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosfelices.backend.business.model.Pedido;
import com.sinensia.pollosfelices.backend.business.services.PedidoServices;
import com.sinensia.pollosfelices.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping
	public List<Pedido> getAll(){
		return pedidoServices.getAll();
	}
	
	@GetMapping("/{numero}")
	public Pedido getByCodigo(@PathVariable Long numero) {
		
		Pedido pedido = pedidoServices.read(numero).orElse(null);
	
		if(pedido == null) {
			throw new PresentationException("No existe el pedido " + numero, HttpStatus.NOT_FOUND);
		} 
			
		return pedido;
	}
	
}
