package com.sinensia.pollosfelices.backend.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinensia.pollosfelices.backend.business.model.Establecimiento;
import com.sinensia.pollosfelices.backend.business.services.EstablecimientoServices;
import com.sinensia.pollosfelices.backend.integration.model.EstablecimientoPL;
import com.sinensia.pollosfelices.backend.integration.repositories.EstablecimientoPLRepository;

@Service
public class EstablecimientoServicesImpl implements EstablecimientoServices {

	@Autowired
	private EstablecimientoPLRepository establecimientoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	@Transactional
	public Long create(Establecimiento establecimiento) {
		
		if(establecimiento.getCodigo() != null) {
			throw new IllegalArgumentException("No se puede crear un establecimiento que ya tiene código.");
		}
		
		Long codigo = System.currentTimeMillis();
		
		establecimiento.setCodigo(codigo);
		
		EstablecimientoPL establecimientoPL = mapper.map(establecimiento, EstablecimientoPL.class);
		
		establecimientoPLRepository.save(establecimientoPL);
		
		return codigo;
	}

	@Override
	public Optional<Establecimiento> read(Long codigo) {
		Optional<EstablecimientoPL> optionalPL = establecimientoPLRepository.findById(codigo);
		return optionalPL.isEmpty() ? Optional.empty() : Optional.of(mapper.map(optionalPL.get(), Establecimiento.class));	
	}

	@Override
	public List<Establecimiento> getAll() {
	
		return establecimientoPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Establecimiento.class))
				.collect(Collectors.toList());
	}

}
