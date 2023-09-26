package com.sinensia.pollosfelices.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosfelices.backend.business.model.Camarero;

public interface CamareroServices {
	
	/**
	 * Lanza IllegalStateException si:
	 * 
	 * - El camarero tiene DNI null
	 * - El camarero tiene id null
	 * - Ya existe un camarero con el mismo DNI
	 * 
	 */
	Long create(Camarero camarero);
	
	Optional<Camarero> read(Long id);
	Optional<Camarero> read(String dni);
	
	void update(Camarero camarero);
	void delete(Long id);
	
	List<Camarero> getAll();
	List<Camarero> getByNombreLikeIgnoreCase(String texto);

	int getNumeroTotalCamareros();
	
}
