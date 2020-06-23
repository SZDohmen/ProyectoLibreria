package com.libreria.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.main.dao.IFacturaCompraDao;
import com.libreria.main.dao.IProductoDao;
import com.libreria.main.dao.IProveedorDao;
import com.libreria.main.entities.FacturaCompra;
import com.libreria.main.entities.Producto;
import com.libreria.main.entities.Proveedor;

@Service
public class ProveedorService implements IPersonaService <Proveedor, FacturaCompra> {

	@Autowired
	private IProveedorDao proveedorDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	public IFacturaCompraDao facturaCompraDao;
	
	
	//FIND ALL ----------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public List<Proveedor> findAll() {
		return (List<Proveedor>) proveedorDao.findAll();
	}

	
	//SAVE --------------------------------------------------------------------------------
	@Override
	@Transactional
	public void save(Proveedor proveedor) {
		proveedorDao.save(proveedor);
		
	}

	
	//FIND BY ID --------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public Proveedor findById(Long id) {
		return proveedorDao.findById(id).orElse(null);
	}

	
	//DELETE BY ID ------------------------------------------------------------------------
	@Override
	@Transactional
	public void deleteById(Long id) {
		proveedorDao.deleteById(id);		
	}

	
	//FIND POR NOMBRE ---------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%" + term + "%"); //buscar por nombre ignorando si es con mayúscula o minúscula
	}

	
	//SAVE FACTURA VENTA ------------------------------------------------------------------
	@Override
	@Transactional
	public void saveFactura(FacturaCompra facturaCompra) {
		facturaCompraDao.save(facturaCompra);
	}

	
	//FIND PRODUCTO BY ID -----------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	
	//FIND FACTURA VENTA BY ID ------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public FacturaCompra findFacturaById(Long id) {
		return facturaCompraDao.findById(id).orElse(null);
	}

	
	//DELETE FACTURA ----------------------------------------------------------------------
	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaCompraDao.deleteById(id);
	}

}
