package com.sinensia.pollosfelices.backend.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosfelices.backend.business.model.Categoria;
import com.sinensia.pollosfelices.backend.business.services.CategoriaServices;
import com.sinensia.pollosfelices.backend.integration.model.CategoriaPL;
import com.sinensia.pollosfelices.backend.integration.repositories.CategoriaPLRepository;

@Service
public class CategoriaServicesImpl implements CategoriaServices {

	@Autowired
	private CategoriaPLRepository categoriaPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Long create(Categoria categoria) {
		
		if(categoria.getCodigo() != null) {
			throw new IllegalStateException("Para crear una categoría el código debe ser null");
		}
		
		Long codigo = System.currentTimeMillis();
		categoria.setCodigo(codigo);
		
		CategoriaPL categoriaPL = mapper.map(categoria, CategoriaPL.class);
		
		categoriaPLRepository.save(categoriaPL);
		
		return codigo;
	}

	@Override
	public Optional<Categoria> read(Long codigo) {
		Optional<CategoriaPL> optionalPL = categoriaPLRepository.findById(codigo);
		return optionalPL.isEmpty() ? Optional.empty() : Optional.of(mapper.map(optionalPL.get(), Categoria.class));			
	}

	@Override
	public List<Categoria> getAll() {
		return categoriaPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Categoria.class))
				.collect(Collectors.toList());
	}

}
