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

import com.libreria.main.entities.Proveedor;
import com.libreria.main.service.ProveedorService;

@Controller
@RequestMapping("/proveedor")
@SessionAttributes("proveedor")
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;
	
	
	//VER ---------------------------------------------------------------------------------
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Proveedor proveedor = proveedorService.findById(id);
		
		if(proveedor==null) {
			flash.addFlashAttribute("error", "El proveedor no existe.");
			return "redirect:/proveedor/listar";
		}
		
		model.put("proveedor", proveedor);
		model.put("titulo", "Detalles del proveedor " + proveedor.getNombre() + " " + proveedor.getApellido());
		
		return "proveedor/ver";
	}
	
	
	//LISTAR ------------------------------------------------------------------------------
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de proveedores");
		model.addAttribute("proveedores", proveedorService.findAll());
		
		return "proveedor/listar";
	}
	
	
	//CREAR -------------------------------------------------------------------------------
	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		
		Proveedor proveedor = new Proveedor();
		
		model.put("proveedor", proveedor);
		model.put("titulo", "Formulario de Proveedor");
		model.put("boton", "Crear proveedor");
		
		return "proveedor/form";
	}
	
	
	//EDITAR ------------------------------------------------------------------------------
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Proveedor proveedor = null;
		
		if(id > 0) {
			proveedor = proveedorService.findById(id);
			
			if(proveedor == null) {
				flash.addFlashAttribute("error", "El ID del proveedor no existe.");
				return "redirect:/proveedor/listar";
			}
			
		} else {
			flash.addFlashAttribute("error", "¡El ID del proveedor no puede ser cero!");
			return "redirect:/proveedor/listar";
		}
		
		model.put("proveedor", proveedor);
		model.put("titulo", "Editar Proveedor");
		model.put("boton", "Editar proveedor");
		
		return "proveedor/form";
	}
	
	
	//GUARDAR -----------------------------------------------------------------------------
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Proveedor proveedor, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) { 
		
		if(result.hasErrors()) { 
			model.addAttribute("titulo", "Formulario de Proveedor");
			model.addAttribute("boton", "Crear proveedor");
			return "proveedor/form";
		}
		
		String mensajeFlash;
		if(proveedor.getId() != null) {
			mensajeFlash = "¡Proveedor editado con éxito!";
		
		} else {
			mensajeFlash = "¡Proveedor creado con éxito!";
		}
		
		proveedorService.save(proveedor);
		status.setComplete(); 
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/proveedor/listar";
	}

	
	//ELIMINAR ----------------------------------------------------------------------------
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			proveedorService.deleteById(id);
		}
		
		flash.addFlashAttribute("success", "Proveedor eliminado con éxito.");
		return "redirect:/proveedor/listar";
	}
	
}
