package com.sinensia.pollosfelices.backend.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sinensia.pollosfelices.backend.business.model.Camarero;
import com.sinensia.pollosfelices.backend.business.services.CamareroServices;

@Service
public class CamareroServicesImpl implements CamareroServices{

	@Override
	public Long create(Camarero camarero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Camarero camarero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Camarero> read(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Camarero> read(String dni) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Camarero> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Camarero> getByNombreLikeIgnoreCase(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumeroTotalCamareros() {
		// TODO Auto-generated method stub
		return 0;
	}

}
