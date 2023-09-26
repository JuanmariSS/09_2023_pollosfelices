package com.sinensia.pollosfelices.backend.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosfelices.backend.business.model.Cliente;
import com.sinensia.pollosfelices.backend.business.services.ClienteServices;
import com.sinensia.pollosfelices.backend.integration.model.ClientePL;
import com.sinensia.pollosfelices.backend.integration.repositories.ClientePLRepository;

@Service
public class ClienteServicesImpl implements ClienteServices {

	@Autowired
	private ClientePLRepository clientePLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public List<Cliente> getAll() {
		
		return clientePLRepository.findAll().stream()
				.map(x -> mapper.map(x, Cliente.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Cliente> read(Long id) {
		Optional<ClientePL> optionalPL = clientePLRepository.findById(id);
		return optionalPL.isEmpty() ? Optional.empty() : Optional.of(mapper.map(optionalPL.get(), Cliente.class));	
	}

}
