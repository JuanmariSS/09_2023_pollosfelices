package com.sinensia.pollosfelices.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.pollosfelices.business.model.Cliente;
import com.sinensia.pollosfelices.business.services.ClienteServices;
import com.sinensia.pollosfelices.presentation.config.PresentationException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteServices clienteServices;
	
	@GetMapping
	public List<Cliente> getAll(){
		return clienteServices.getAll();
	}
	
	@GetMapping("/{id}")
	public Cliente getById(@PathVariable Long id) {
		
		Cliente cliente = clienteServices.read(id).orElse(null);

		if(cliente == null) {
			throw new PresentationException("Ooops... No existe el cliente " + id, HttpStatus.NOT_FOUND);
		}
		
		return cliente;
	}
}
