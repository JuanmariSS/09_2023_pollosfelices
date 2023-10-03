package com.sinensia.pollosfelices.backend.business.model.dtos;

import java.io.Serializable;

public class Producto1DTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nombre;
	private Double precio;
	
	public Producto1DTO() {
		
	}
	
	public Producto1DTO(String nombre, Double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public String getNombre() {
		return nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Producto1DTO [nombre=" + nombre + ", precio=" + precio + "]";
	}
	
}
