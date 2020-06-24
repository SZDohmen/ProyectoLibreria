package com.libreria.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.libreria.main.service.ProductoService;

@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	
	//LISTAR ------------------------------------------------------------------------------
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoService.findAll());
		
		return "producto/listar";
	}
	
	
	
			
}
