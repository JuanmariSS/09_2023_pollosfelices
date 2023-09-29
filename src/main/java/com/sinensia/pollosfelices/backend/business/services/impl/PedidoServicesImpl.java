package com.sinensia.pollosfelices.backend.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosfelices.backend.business.model.Pedido;
import com.sinensia.pollosfelices.backend.business.services.PedidoServices;
import com.sinensia.pollosfelices.backend.integration.model.PedidoPL;
import com.sinensia.pollosfelices.backend.integration.repositories.PedidoPLRepository;

@Service
public class PedidoServicesImpl implements PedidoServices {

	@Autowired
	private PedidoPLRepository pedidoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Long create(Pedido pedido) {
		
		if(pedido.getNumero() != null) {
			throw new IllegalArgumentException("No se puede crear un pedido que ya tiene número.");
		}
		
		// TODO establecer el estado en NUEVO
		
		// TODO Comprobar existencia Camarero
		// TODO Comprobar existencia Establecimiento
		
		Long numero = System.currentTimeMillis();
		
		pedido.setNumero(numero);
		
		PedidoPL pedidoPL = mapper.map(pedido, PedidoPL.class);
		
		pedidoPLRepository.save(pedidoPL);
		
		return numero;
	}

	@Override
	public Optional<Pedido> read(Long numero) {
		Optional<PedidoPL> optionalPL = pedidoPLRepository.findById(numero);
		return optionalPL.isEmpty() ? Optional.empty() : Optional.of(mapper.map(optionalPL.get(), Pedido.class));	
	}

	@Override
	public List<Pedido> getAll() {
		
		return pedidoPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Pedido.class))
				.collect(Collectors.toList());
		            
	}

	@Override
	public void procesar(Long numero) {
		// TODO Auto-generated method stub
		
		// Nota: Recomiemdo crear una @Query de tipo UPDATE (mirar JPAShowCase) para llevar a cabo la actualiación. 
		
	}

	@Override
	public void entregar(Long numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void servir(Long numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelar(Long numero) {
		// TODO Auto-generated method stub
		
	}

}
