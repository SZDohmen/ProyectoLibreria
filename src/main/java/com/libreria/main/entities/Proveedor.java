package com.libreria.main.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	private Integer telefono;

	@OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<FacturaCompra> facturasCompra;

	
	//CONSTRUCTOR -------------------------------------------------------------------------
	public Proveedor() {
		facturasCompra = new ArrayList<FacturaCompra>();
	}
	
		
	//GETTERS Y SETTERS -------------------------------------------------------------------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public List<FacturaCompra> getFacturasCompra() {
		return facturasCompra;
	}

	public void setFacturasCompra(List<FacturaCompra> facturasCompra) {
		this.facturasCompra = facturasCompra;
	}
	
	
	//AGREGAR FACTURA A LA LISTA ----------------------------------------------------------
	public void agregarFacturaCompra(FacturaCompra facturaCompra) {
		facturasCompra.add(facturaCompra);
	}
		
		
	//MOSTRAR NOMBRE COMPLETO PROVEEDOR EN facturaCompra/ver.html -------------------------
	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
		
}
