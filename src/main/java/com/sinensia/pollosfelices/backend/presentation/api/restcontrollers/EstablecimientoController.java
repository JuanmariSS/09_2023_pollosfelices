package com.sinensia.pollosfelices.backend.presentation.api.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.pollosfelices.backend.business.model.Establecimiento;
import com.sinensia.pollosfelices.backend.business.services.EstablecimientoServices;
import com.sinensia.pollosfelices.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/establecimientos")
public class EstablecimientoController {

	@Autowired
	private EstablecimientoServices establecimientoServices;
	
	@GetMapping
	public List<Establecimiento> getAll(){
		return establecimientoServices.getAll();
	}
	
	@GetMapping("/{codigo}")
	public Establecimiento getByCodigo(@PathVariable Long codigo) {
		
		Establecimiento establecimiento = establecimientoServices.read(codigo).orElse(null);
	
		if(establecimiento == null) {
			throw new PresentationException("No existe el establecimiento " + codigo, HttpStatus.NOT_FOUND);
		} 
			
		return establecimiento;	
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Establecimiento establecimiento, UriComponentsBuilder ucb){
	
		Long codigo = establecimientoServices.create(establecimiento);
		
		return ResponseEntity.created(ucb.path("/establecimientos/{codigo}")
				.build(codigo))
				.build();
	}
	
}
