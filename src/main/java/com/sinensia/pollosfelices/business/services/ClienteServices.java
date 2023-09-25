package com.sinensia.pollosfelices.business.services;

import java.util.List;
import java.util.Optional;

import com.sinensia.pollosfelices.business.model.Cliente;

public interface ClienteServices {

	List<Cliente> getAll();
	
	Optional<Cliente> read(Long id);
}
