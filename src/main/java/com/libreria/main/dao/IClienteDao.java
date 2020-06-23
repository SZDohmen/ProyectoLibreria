package com.libreria.main.dao;

import org.springframework.data.repository.CrudRepository;

import com.libreria.main.entities.Cliente;

//No es necesario anotarlo como @Repository porque hereda de un CrudRepository
public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
