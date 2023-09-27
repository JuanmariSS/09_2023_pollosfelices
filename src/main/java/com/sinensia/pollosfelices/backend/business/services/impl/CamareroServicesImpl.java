package com.sinensia.pollosfelices.backend.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinensia.pollosfelices.backend.business.model.Camarero;
import com.sinensia.pollosfelices.backend.business.services.CamareroServices;
import com.sinensia.pollosfelices.backend.integration.model.CamareroPL;
import com.sinensia.pollosfelices.backend.integration.repositories.CamareroPLRepository;

@Service
public class CamareroServicesImpl implements CamareroServices{

	@Autowired
	private CamareroPLRepository camareroPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	@Transactional
	public Long create(Camarero camarero) {
		
		if(camarero.getDni() == null) {
			throw new IllegalStateException("No se puede crear un camarero con DNI null");
		}
		
		if(camarero.getId() != null) {
			throw new IllegalStateException("No se puede crear un camarero con Id diferente a null");
		}
		
		boolean existeByDNI = false;
		
		// TODO Mirar si existe por DNI!
		
		if(existeByDNI) {
			throw new IllegalStateException("Ya existe el camarero con DNI " + camarero.getDni());
		}
		
		Long id = System.currentTimeMillis();
		camarero.setId(id);
		
		CamareroPL camareroPL = mapper.map(camarero, CamareroPL.class);
		
		camareroPLRepository.save(camareroPL);
			
		return id;
	}
	
	@Override
	public Optional<Camarero> read(Long id) {
		Optional<CamareroPL> optionalPL = camareroPLRepository.findById(id);
		return optionalPL.isEmpty() ? Optional.empty() : Optional.of(mapper.map(optionalPL.get(), Camarero.class));	
	}

	@Override
	public Optional<Camarero> read(String dni) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	@Transactional
	public void update(Camarero camarero) {
		
		Long id = camarero.getId();
		
		if(id == null) {
			throw new IllegalArgumentException("No se puede actualizar un camarero con ID null.");
		}
		
		boolean existe = camareroPLRepository.existsById(id);
		
		if(!existe) {
			throw new IllegalArgumentException("No existe el camarero con ID " + id);
		}
		
		CamareroPL camareroPL = mapper.map(camarero, CamareroPL.class);
		
		camareroPLRepository.save(camareroPL);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		
		boolean existe = camareroPLRepository.existsById(id);
		
		if(!existe) {
			throw new IllegalArgumentException("No existe el camarero con ID " + id);
		}
		
		camareroPLRepository.deleteById(id);
		
	}

	@Override
	public List<Camarero> getAll() {
		
		return camareroPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Camarero.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Camarero> getByNombreLikeIgnoreCase(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroTotalCamareros() {
		return (int) camareroPLRepository.count();
	}

}
