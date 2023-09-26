package com.sinensia.pollosfelices.backend.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosfelices.backend.business.model.Pedido;
import com.sinensia.pollosfelices.backend.business.services.PedidoServices;
import com.sinensia.pollosfelices.backend.integration.repositories.PedidoPLRepository;

@Service
public class PedidoServicesImpl implements PedidoServices {

	@Autowired
	private PedidoPLRepository pedidoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Long create(Pedido pedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Pedido> read(Pedido pedido) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Pedido> getAll() {
		
		return pedidoPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Pedido.class))
				.collect(Collectors.toList());
		            
	}

}
