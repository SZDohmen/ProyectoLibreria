package com.libreria.main.entities;

import java.io.Serializable;
//import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Stock implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	
	@Temporal(TemporalType.DATE)
	//private Date fecha;

	
	//GENERAR FECHA -----------------------------------------------------------------------
//	@PrePersist
//	public void generarFecha() {
//		fecha = new Date();
//	}
	
	
	//GETTERS Y SETTERS -------------------------------------------------------------------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

//	public Date getFecha() {
//		return fecha;
//	}
//
//	public void setFecha(Date fecha) {
//		this.fecha = fecha;
//	}
	
}
