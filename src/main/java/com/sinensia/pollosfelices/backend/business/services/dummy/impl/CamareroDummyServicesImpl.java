package com.sinensia.pollosfelices.backend.business.services.dummy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sinensia.pollosfelices.backend.business.model.Camarero;
import com.sinensia.pollosfelices.backend.business.model.DatosContacto;
import com.sinensia.pollosfelices.backend.business.model.Direccion;
import com.sinensia.pollosfelices.backend.business.services.CamareroServices;

public class CamareroDummyServicesImpl implements CamareroServices{

	private final Map<Long, Camarero> CAMAREROS = new HashMap<>();
	
	public CamareroDummyServicesImpl() {
		init();
	}
	
	@Override
	public Long create(Camarero camarero) {
		
		if(camarero.getDni() == null) {
			throw new IllegalStateException("No se puede crear un camarero con DNI null");
		}
		
		if(camarero.getId() != null) {
			throw new IllegalStateException("No se puede crear un camarero con Id diferente a null");
		}
		
		boolean existe = false;
		
		for(Camarero camarero1: CAMAREROS.values()) {
			if(camarero1.getDni().equals(camarero.getDni())) {
				existe = true;
				break;
			}
		}
		
		if(existe) {
			throw new IllegalStateException("Ya existe el camarero con DNI " + camarero.getDni());
		}
		
		Long id = System.currentTimeMillis();
		camarero.setId(id);
		
		CAMAREROS.put(camarero.getId(), camarero);
		
		return id;
		
	}
	
	@Override
	public void update(Camarero camarero) {
		
		Long id = camarero.getId();
		
		if(id == null) {
			throw new IllegalArgumentException("No se puede actualizar un camarero con id null.");
		}
		
		boolean existe = CAMAREROS.containsKey(id);
		
		if(!existe) {
			throw new IllegalArgumentException("No existe el camarero con id " + id);
		}
		
		CAMAREROS.replace(id, camarero);
		
	}

	@Override
	public void delete(Long id) {
		
		boolean existe = CAMAREROS.containsKey(id);
		
		if(!existe) {
			throw new IllegalArgumentException("No existe el camarero con id " + id);
		}
		
		CAMAREROS.remove(id);
		
	}

	@Override
	public Optional<Camarero> read(String dni) {
			
		for(Camarero camarero: CAMAREROS.values()) {
			if(camarero.getDni().equals(dni)) {
				return Optional.of(camarero);
			}
		}
		
		return Optional.empty();
	}
	
	@Override
	public Optional<Camarero> read(Long id) {
		
		Camarero camarero = CAMAREROS.get(id);
	
		return camarero == null ? Optional.empty() : Optional.of(camarero);
	}
	
	@Override
	public List<Camarero> getAll() {
		return new ArrayList<>(CAMAREROS.values());
	}
	
	@Override
	public List<Camarero> getByNombreLikeIgnoreCase(String texto) {
		
		return CAMAREROS.values().stream()
				.filter(x -> x.getNombre().toLowerCase().contains(texto.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public int getNumeroTotalCamareros() {
		return CAMAREROS.size();
	}
	
	// *********************************************************
	//
	// Private Methods
	//
	// *********************************************************
	
	private void init() {
		
		Camarero c1 = new Camarero();
		Camarero c2 = new Camarero();
		
		c1.setId(100L);
		c1.setDni("46722323R");
		c1.setNombre("Honorio");
		c1.setApellido1("Martín");
		c1.setApellido2("Salvador");
		c1.setLicenciaManipuladorAlimentos("LMA671192222");
		
		Direccion direccion1 = new Direccion();
		direccion1.setDireccion("Avda de los Pinos 45 2º 4ª");
		direccion1.setPoblacion("Santa Perpetua de Mogoda");
		direccion1.setCodigoPostal("08098");
		direccion1.setProvincia("Barcelona");
		direccion1.setPais("España");
		
		DatosContacto datosContacto1 = new DatosContacto();
		datosContacto1.setTelefono("93 2456776");
		datosContacto1.setEmail("honorio445@hotmail.com");
		
		c1.setDireccion(direccion1);
		c1.setDatosContacto(datosContacto1);
		
		c2.setId(101L);
		c2.setDni("43899112T");
		c2.setNombre("Carlota");
		c2.setApellido1("Cifuentes");
		c2.setApellido2("Merino");
		c2.setLicenciaManipuladorAlimentos("LMA671192345");
		
		Direccion direccion2 = new Direccion();
		direccion2.setDireccion("c/ Roble, 34");
		direccion2.setPoblacion("Santa Perpetua de Mogoda");
		direccion2.setCodigoPostal("08098");
		direccion2.setProvincia("Barcelona");
		direccion2.setPais("España");
		
		DatosContacto datosContacto2 = new DatosContacto();
		datosContacto2.setTelefono("6098998233");
		datosContacto2.setEmail("carlota5@hotmail.com");
		
		c2.setDireccion(direccion2);
		c2.setDatosContacto(datosContacto2);
		
		
		CAMAREROS.put(c1.getId(), c1);
		CAMAREROS.put(c2.getId(), c2);
	
	}

}
