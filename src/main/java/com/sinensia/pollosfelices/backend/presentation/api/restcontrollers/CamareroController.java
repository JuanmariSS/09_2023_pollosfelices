package com.sinensia.pollosfelices.backend.presentation.api.restcontrollers;

import java.net.URI;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.pollosfelices.backend.business.model.Camarero;
import com.sinensia.pollosfelices.backend.business.services.CamareroServices;
import com.sinensia.pollosfelices.backend.presentation.config.PresentationException;

@RestController
@RequestMapping("/camareros")
public class CamareroController {

	@Autowired
	private CamareroServices camareroServices;
	
	@GetMapping
	public List<Camarero> getAll(@RequestParam(value="texto", required=false) String texto){
		
		List<Camarero> camareros;
		
		if (texto == null) {
			camareros = camareroServices.getAll();
		} else {
			camareros = camareroServices.getByNombreLikeIgnoreCase(texto);
		}
		
		return camareros;
	}
	
	@GetMapping("/{id}")
	public Camarero getById(@PathVariable Long id) {
		
		Camarero camarero = camareroServices.read(id).orElse(null);
	
		if(camarero == null) {
			throw new PresentationException("Ooops... No existe el camarero " + id, HttpStatus.NOT_FOUND);
		} 
			
		return camarero;
		
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Camarero camarero, UriComponentsBuilder ucb){
				
		Long id = camareroServices.create(camarero);
		
		URI uri = ucb.path("/camareros/{id}").build(id);
		
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		
		Optional<Camarero> optional = camareroServices.read(id);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No existe el Camarero con id " + id + ". No se ha podido eliminar.", HttpStatus.NOT_FOUND);
		}
		
		camareroServices.delete(id);
		
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Camarero camarero) {
		camareroServices.update(camarero);	
	}

}
