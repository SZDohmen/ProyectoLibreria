package com.libreria.main.dao;

import org.springframework.data.repository.CrudRepository;

import com.libreria.main.entities.FacturaCompra;

public interface IFacturaCompraDao extends CrudRepository<FacturaCompra, Long> {

}
