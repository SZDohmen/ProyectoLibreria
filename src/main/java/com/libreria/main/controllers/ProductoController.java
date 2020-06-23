package com.libreria.main.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libreria.main.entities.Producto;
import com.libreria.main.service.ProductoService;
import com.libreria.main.service.ProveedorService;

@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	
	//LISTAR ------------------------------------------------------------------------------
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoService.findAll());
		model.addAttribute("proveedores", proveedorService.findAll());
		
		return "producto/listar";
	}


	//CREAR -------------------------------------------------------------------------------
	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {
			
		Producto producto = new Producto();
			
		model.put("producto", producto);
		model.put("titulo", "Formulario de Producto");
		model.put("boton", "Agregar producto");
			
		return "producto/form";
	}
		
		
	//GUARDAR -----------------------------------------------------------------------------
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) { 
			
		if(result.hasErrors()) { //result es para manejar los errores
			model.addAttribute("titulo", "Formulario de Producto");
			model.addAttribute("boton", "Crear producto");
			return "producto/form";	
		}
		
		
			
		producto.getPrecio().setPrecioVenta(producto.getPrecio().getPrecioCompra());
		productoService.save(producto);
		status.setComplete();
		flash.addFlashAttribute("success", "Producto creado con éxito!");
			
		return "redirect:/producto/listar";
		
	}
		
}
