package com.sinensia.pollosfelices.backend.auditoria.business.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.sinensia.pollosfelices.backend.auditoria.business.model.RequestLog;

public interface RequestLogServices {

	/**
	 * Devuelve el la ID que le ha otorgado el sistema al log.
	 * 
	 * Si la ID del log no es null lanza IllegalStateException
	 * 
	 */
	Long create(RequestLog requestLog);
	
	Optional<RequestLog> read(Long id);
	
	/**
	 * La lista viene ordenada por fecha en orden descendente
	 */
	List<RequestLog> getAll();
	
	/**
	 * La lista viene ordenada por fecha en orden descendente
	 */
	List<RequestLog> getBetweenDates(Date desde, Date hasta);
	
}
