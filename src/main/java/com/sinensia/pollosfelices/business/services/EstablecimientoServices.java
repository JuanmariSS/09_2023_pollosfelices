package com.sinensia.pollosfelices.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosfelices.business.model.Establecimiento;

public interface EstablecimientoServices {

	/**
	 * Devuelve el código que le ha otorgado el sistema al nuevo establecimiento.
	 * 
	 * Si el código del establecimiento no es null lanza IllegalStateException
	 * 
	 */
	Long create(Establecimiento establecimiento);
	
	Optional<Establecimiento> read(Long codigo);
	
	List<Establecimiento> getAll();
}
