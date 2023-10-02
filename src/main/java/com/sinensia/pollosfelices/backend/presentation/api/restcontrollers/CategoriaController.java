package com.sinensia.pollosfelices.backend.presentation.api.restcontrollers;

import java.net.URI;
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

import com.sinensia.pollosfelices.backend.business.model.Categoria;
import com.sinensia.pollosfelices.backend.business.services.CategoriaServices;
import com.sinensia.pollosfelices.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaServices categoriaServices;
	
	@GetMapping
	public List<Categoria> getAll(){
		return categoriaServices.getAll();
	}
	
	@GetMapping("/{codigo}")
	public Categoria getByCodigo(@PathVariable Long codigo) {
		
		Categoria categoria = categoriaServices.read(codigo).orElse(null);
		
		if(categoria == null) {
			throw new PresentationException("No existe la categoria " + codigo, HttpStatus.NOT_FOUND);
		}
		
		return categoria;
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Categoria categoria, UriComponentsBuilder ucb){
		
		Long codigo = categoriaServices.create(categoria);
		
		URI uri = ucb.path("/categorias/{codigo}").build(codigo);
		
		return ResponseEntity.created(uri).build();
	}

}
