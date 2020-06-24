package com.libreria.main.service;

import java.util.List;

import com.libreria.main.entities.Producto;

public interface IPersonaService <ENTITY, FACTURA> {
	
	public List<ENTITY> findAll();
	
	public void save(ENTITY entity);
	
	public ENTITY findById(Long id);
	
	public void deleteById(Long id);
	
	public List<Producto> findByNombre(String term);
	
	public void saveFactura(FACTURA factura);
	
	public List<FACTURA> findAllFacturas();
	
	public Producto findProductoById(Long id);
	
	public FACTURA findFacturaById(Long id);
	
	public void deleteFactura(Long id);
	
}
