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
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty //solo para String
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	private Integer telefono;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) //un cliente, muchas facturas. FetchType.LAZY: forma de carga para evitar q traiga todo de una vez, por cada factura va a traer a su cliente
	private List<FacturaVenta> facturasVenta;					  //CascadeType.ALL: todas las operaciones se van a realizar en cadena. Se persiste el cliente y también sus facturas.


	//CONSTRUCTOR -------------------------------------------------------------------------
	public Cliente() {
		facturasVenta = new ArrayList<FacturaVenta>();
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

	public List<FacturaVenta> getFacturasVenta() {
		return facturasVenta;
	}

	public void setFacturasVenta(List<FacturaVenta> facturasVenta) {
		this.facturasVenta = facturasVenta;
	}
	
	
	//AGREGAR FACTURA A LA LISTA ----------------------------------------------------------
	public void agregarFacturaVenta(FacturaVenta facturaVenta) {
		facturasVenta.add(facturaVenta);
	}
	
	
	//MOSTRAR NOMBRE COMPLETO CLIENTE EN facturaVenta/ver.html ----------------------------
	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
	
}
