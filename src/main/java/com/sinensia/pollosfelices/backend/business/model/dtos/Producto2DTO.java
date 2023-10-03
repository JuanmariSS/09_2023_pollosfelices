package com.sinensia.pollosfelices.backend.business.model.dtos;

import java.io.Serializable;

public class Producto2DTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String fechaAlta;
	
	public Producto2DTO() {
		
	}
	
	public Producto2DTO(String nombre, String fechaAlta) {
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "Producto2DTO [nombre=" + nombre + ", fechaAlta=" + fechaAlta + "]";
	}
	
}
