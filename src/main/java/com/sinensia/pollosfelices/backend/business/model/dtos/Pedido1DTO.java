package com.sinensia.pollosfelices.backend.business.model.dtos;

import java.io.Serializable;
import java.util.Date;

public class Pedido1DTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long numero;
	private Date fecha;
	private String establecimiento;
	private String nombreCamarero;
	private String nombreCliente;
	private int numeroLineas;
	private String estado;
	
	public Pedido1DTO() {
		
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstablecimiento() {
		return establecimiento;
	}

	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}

	public String getNombreCamarero() {
		return nombreCamarero;
	}

	public void setNombreCamarero(String nombreCamarero) {
		this.nombreCamarero = nombreCamarero;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public int getNumeroLineas() {
		return numeroLineas;
	}

	public void setNumeroLineas(int numeroLineas) {
		this.numeroLineas = numeroLineas;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Pedido1DTO [numero=" + numero + ", fecha=" + fecha + ", establecimiento=" + establecimiento
				+ ", nombreCamarero=" + nombreCamarero + ", nombreCliente=" + nombreCliente + ", numeroLineas="
				+ numeroLineas + ", estado=" + estado + "]";
	}
	
}
