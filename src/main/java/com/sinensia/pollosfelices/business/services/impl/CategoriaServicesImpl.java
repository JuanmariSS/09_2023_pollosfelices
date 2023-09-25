package com.sinensia.pollosfelices.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.pollosfelices.business.model.Categoria;
import com.sinensia.pollosfelices.business.services.CategoriaServices;
import com.sinensia.pollosfelices.integration.repositories.CategoriaRepository;

@Service
public class CategoriaServicesImpl implements CategoriaServices {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public Long create(Categoria categoria) {
		
		if(categoria.getCodigo() != null) {
			throw new IllegalStateException("Para crear una categoría el código debe ser null");
		}
		
		Long codigo = System.currentTimeMillis();
		categoria.setCodigo(codigo);
		categoriaRepository.save(categoria);
		
		return codigo;
	}

	@Override
	public Optional<Categoria> read(Long codigo) {
		return categoriaRepository.findById(codigo);	
	}

	@Override
	public List<Categoria> getAll() {
		return categoriaRepository.findAll();
	}

}
