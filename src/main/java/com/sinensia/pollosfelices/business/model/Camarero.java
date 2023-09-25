package com.sinensia.pollosfelices.business.model;

public class Camarero extends Persona {
	private static final long serialVersionUID = 1L;

	private String licenciaManipuladorAlimentos; 
	
	public Camarero() {
		
	}

	public String getLicenciaManipuladorAlimentos() {
		return licenciaManipuladorAlimentos;
	}

	public void setLicenciaManipuladorAlimentos(String licenciaManipuladorAlimentos) {
		this.licenciaManipuladorAlimentos = licenciaManipuladorAlimentos;
	}

	@Override
	public String toString() {
		return "Camarero [licenciaManipuladorAlimentos=" + licenciaManipuladorAlimentos + ", toString()="
				+ super.toString() + "]";
	}
	
}
