package com.libreria.main.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libreria.main.entities.Cliente;
import com.libreria.main.entities.FacturaVenta;
import com.libreria.main.entities.ItemFVenta;
import com.libreria.main.entities.Producto;
import com.libreria.main.service.ClienteService;

@Controller
@RequestMapping("/facturaVenta")
@SessionAttributes("facturaVenta")
public class FacturaVentaController {

	@Autowired
	private ClienteService clienteService;
	
	
	//VER ---------------------------------------------------------------------------------
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		FacturaVenta facturaVenta = clienteService.findFacturaById(id);
		
		if(facturaVenta == null) {
			flash.addFlashAttribute("error", "La factura no existe.");
			return "redirect:/cliente/listar";
		}
		
		model.addAttribute("facturaVenta", facturaVenta);
		model.addAttribute("titulo", "Factura de Venta");
		
		return "facturaVenta/ver";
	}
	
	
	//CREAR -------------------------------------------------------------------------------
	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value="clienteId") Long clienteId, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findById(clienteId);
		
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe.");
			return "redirect:/cliente/listar";
		}
		
		FacturaVenta facturaVenta = new FacturaVenta();
		facturaVenta.setCliente(cliente);
		model.put("facturaVenta", facturaVenta);
		model.put("titulo", "Crear Factura");
		
		return "facturaVenta/form";
	}
	
	
	//CARGAR PRODUCTOS --------------------------------------------------------------------
	@GetMapping(value="/cargar-productos/{term}", produces= {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		return clienteService.findByNombre(term);
	}
	
	
	//GUARDAR -----------------------------------------------------------------------------
	@PostMapping("/form")
	public String guardar(@Valid FacturaVenta facturaVenta, BindingResult result, Model model,
			@RequestParam(name="item_id[]", required=false) Long[] itemId, 
			@RequestParam(name="cantidad[]", required=false) Integer[] cantidad, 
			RedirectAttributes flash, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "facturaVenta/form";
		}
		    
		if(itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "Error: ¡la factura DEBE tener líneas!");
			return "facturaVenta/form";
		}
		
		for(int i=0; i<itemId.length; i++) {
			Producto producto = clienteService.findProductoById(itemId[i]);
			
			if(cantidad[i] <= producto.getStock().getCantidad()) {	//comprobamos que la cantidad que pide el cliente no supera la existente en nuestro stock
				ItemFVenta item = new ItemFVenta();
				item.setCantidad(cantidad[i]);
				item.setProducto(producto);
				
				facturaVenta.agregarItemFVenta(item);
				producto.getStock().setCantidad(producto.getStock().getCantidad() - cantidad[i]);
				
			} else {
				model.addAttribute("titulo", "Crear Factura");
				model.addAttribute("error", "Error: No hay suficiente stock de " + producto.getNombre());
				return "facturaVenta/form";
			}
		
		}
		
		clienteService.saveFactura(facturaVenta);
		status.setComplete(); //finalizar la sesión
		
		flash.addFlashAttribute("success", "¡Factura creada con éxito!");
		
		return "redirect:/cliente/ver/" + facturaVenta.getCliente().getId();
	}
	
	
	//ELIMINAR ----------------------------------------------------------------------------
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		FacturaVenta facturaVenta = clienteService.findFacturaById(id);
		
		if(facturaVenta != null) {
			clienteService.deleteFactura(id);
			flash.addFlashAttribute("success", "Factura eliminada con éxito.");
			return "redirect:/cliente/ver/" + facturaVenta.getCliente().getId();
		}
		
		flash.addFlashAttribute("error", "La factura no se puede eliminar porque no existe.");
		return "redirect:/cliente/listar";
	}
	
}
