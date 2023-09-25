package com.sinensia.pollosfelices.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosfelices.business.model.Categoria;

public interface CategoriaServices {

	/**
	 * Devuelve el código que le ha otorgado el sistema a la nueva categoría.
	 * 
	 * Si el código de la categoría no es null lanza IllegalStateException
	 * 
	 */
	Long create(Categoria categoria);
	
	Optional<Categoria> read(Long codigo);
	
	List<Categoria> getAll();
}
