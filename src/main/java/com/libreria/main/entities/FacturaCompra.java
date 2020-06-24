package com.libreria.main.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "facturas_compra")
public class FacturaCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull  
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY) //muchas facturas, un cliente
	private Proveedor proveedor;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "factura_compra_id") //es necesario cuando la relación es unidireccional
	private List<ItemFCompra> items;
	
	
	//GENERAR FECHA -----------------------------------------------------------------------
	@PrePersist
	public void generarFecha() {
		fecha = new Date();
	}
	
	
	//CONSTRUCTOR -------------------------------------------------------------------------
	public FacturaCompra() {
		items = new ArrayList<ItemFCompra>();
	}

	
	//SETTERS Y GETTERS -------------------------------------------------------------------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public List<ItemFCompra> getItems() {
		return items;
	}

	public void setItems(List<ItemFCompra> items) {
		this.items = items;
	}
	
	
	//AGREGAR ITEM A LA LISTA -------------------------------------------------------------
	public void agregarItemFCompra(ItemFCompra item) {
		this.items.add(item);
	}
	
	
	//OBTENER VALOR TOTAL -----------------------------------------------------------------
	public Double getTotal() {
		Double total = 0.00;
		
		for(int i=0; i<items.size(); i++) {
			total += items.get(i).calcularImporte();
		}
		return total;
	}
	
}
