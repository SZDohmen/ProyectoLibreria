package com.libreria.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreria.main.dao.IProductoDao;
import com.libreria.main.entities.Producto;

@Service
public class ProductoService {

	@Autowired
	private IProductoDao productoDao;
	
	
	//FIND ALL ---------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}
	
	
	//SAVE --------------------------------------------------------------------------------
	@Transactional
	public void save(Producto producto) {
		productoDao.save(producto);
	}
	
}
