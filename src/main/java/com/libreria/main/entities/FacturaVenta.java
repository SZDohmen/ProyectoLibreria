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

@Entity
@Table(name = "facturas_venta")
public class FacturaVenta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY) //muchas facturas, un cliente
	private Cliente cliente;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "factura_venta_id") //es necesario cuando la relación es unidireccional
	private List<ItemFVenta> items;
	
	
	//GENERAR FECHA -----------------------------------------------------------------------
	@PrePersist
	public void generarFecha() {
		fecha = new Date();
	}
		
		
	//CONSTRUCTOR -------------------------------------------------------------------------
	public FacturaVenta() {
		items = new ArrayList<ItemFVenta>();
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFVenta> getItems() {
		return items;
	}

	public void setItems(List<ItemFVenta> items) {
		this.items = items;
	}
	
	
	//AGREGAR ITEM A LA LISTA -------------------------------------------------------------
	public void agregarItemFVenta(ItemFVenta item) {
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
