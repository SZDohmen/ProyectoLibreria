package com.libreria.main.dao;

import org.springframework.data.repository.CrudRepository;

import com.libreria.main.entities.FacturaVenta;

public interface IFacturaVentaDao extends CrudRepository<FacturaVenta, Long> {

}
