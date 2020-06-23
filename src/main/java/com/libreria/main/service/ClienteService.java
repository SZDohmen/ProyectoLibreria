package com.libreria.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.main.dao.IClienteDao;
import com.libreria.main.dao.IFacturaVentaDao;
import com.libreria.main.dao.IProductoDao;
import com.libreria.main.entities.Cliente;
import com.libreria.main.entities.FacturaVenta;
import com.libreria.main.entities.Producto;

@Service
public class ClienteService implements IPersonaService <Cliente, FacturaVenta> {

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	public IFacturaVentaDao facturaVentaDao;
	
	
	//FIND ALL ----------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	
	//SAVE --------------------------------------------------------------------------------
	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
		
	}

	
	//FIND BY ID --------------------------------------------------------------------------
	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	
	//DELETE BY ID ------------------------------------------------------------------------
	@Override
	@Transactional
	public void deleteById(Long id) {
		clienteDao.deleteById(id);		
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
	public void saveFactura(FacturaVenta facturaVenta) {
		facturaVentaDao.save(facturaVenta);
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
	public FacturaVenta findFacturaById(Long id) {
		return facturaVentaDao.findById(id).orElse(null);
	}

	
	//DELETE FACTURA ----------------------------------------------------------------------
	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaVentaDao.deleteById(id);
	}

}
