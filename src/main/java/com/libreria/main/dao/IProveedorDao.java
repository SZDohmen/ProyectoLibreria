package com.libreria.main.dao;

import org.springframework.data.repository.CrudRepository;

import com.libreria.main.entities.Proveedor;

public interface IProveedorDao extends CrudRepository<Proveedor, Long> {

}
