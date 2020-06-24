package com.libreria.main.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libreria.main.entities.Cliente;
import com.libreria.main.service.ClienteService;

@Controller
@RequestMapping("/cliente")
@SessionAttributes("cliente") //el objeto cliente mapeado al formulario se guarda en los atributos de la sesión 
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	
	//VER ---------------------------------------------------------------------------------
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findById(id);
		
		if(cliente==null) {
			flash.addFlashAttribute("error", "El cliente no existe.");
			return "redirect:/cliente/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Detalles del cliente " + cliente.getNombre() + " " + cliente.getApellido());
		
		return "cliente/ver";
	}
	
	
	//LISTAR ------------------------------------------------------------------------------
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		
		return "cliente/listar";
	}
	
	
	//CREAR -------------------------------------------------------------------------------
	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		model.put("boton", "Crear cliente");
		
		return "cliente/form";
	}
	
	
	//EDITAR ------------------------------------------------------------------------------
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if(id > 0) {
			cliente = clienteService.findById(id);
			
			if(cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe.");
				return "redirect:/cliente/listar";
			}
			
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/cliente/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		model.put("boton", "Editar cliente");
		
		return "cliente/form";
	}
	
	
	//GUARDAR -----------------------------------------------------------------------------
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) { //BindingResult SIEMPRE va al lado del objeto del formulario
		
		if(result.hasErrors()) { //result es para manejar los errores
			model.addAttribute("titulo", "Formulario de Cliente");
			model.addAttribute("boton", "Crear cliente");
			return "cliente/form";
		}
		
		String mensajeFlash;
		if(cliente.getId() != null) {
			mensajeFlash = "¡Cliente editado con éxito!";
		
		} else {
			mensajeFlash = "¡Cliente creado con éxito!";
		}
		
		clienteService.save(cliente);
		status.setComplete(); //después de guardar, se elimina el objeto cliente de la sesión y termina el proceso
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/cliente/listar";
	}

	
	//ELIMINAR ----------------------------------------------------------------------------
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			clienteService.deleteById(id);
		}
		
		flash.addFlashAttribute("success", "Cliente eliminado con éxito.");
		return "redirect:/cliente/listar";
	}
	
}
