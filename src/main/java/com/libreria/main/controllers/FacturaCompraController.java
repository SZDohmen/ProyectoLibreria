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

import com.libreria.main.entities.FacturaCompra;
import com.libreria.main.entities.ItemFCompra;
import com.libreria.main.entities.Producto;
import com.libreria.main.entities.Proveedor;
import com.libreria.main.service.ProveedorService;

@Controller
@RequestMapping("/facturaCompra")
@SessionAttributes("facturaCompra")
public class FacturaCompraController {

	@Autowired
	private ProveedorService proveedorService;
	
	
	//VER ---------------------------------------------------------------------------------
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		FacturaCompra facturaCompra = proveedorService.findFacturaById(id);
		
		if(facturaCompra == null) {
			flash.addFlashAttribute("error", "La factura no existe.");
			return "redirect:/proveedor/listar";
		}
		
		model.addAttribute("facturaCompra", facturaCompra);
		model.addAttribute("titulo", "Factura de Compra");
		
		return "facturaCompra/ver";
	}
	
	
	//CREAR -------------------------------------------------------------------------------
	@GetMapping("/form/{proveedorId}")
	public String crear(@PathVariable(value="proveedorId") Long proveedorId, Map<String, Object> model, RedirectAttributes flash) {
		
		Proveedor proveedor = proveedorService.findById(proveedorId);
		
		if(proveedor == null) {
			flash.addFlashAttribute("error", "El proveedor no existe.");
			return "redirect:/proveedor/listar";
		}
		
		FacturaCompra facturaCompra = new FacturaCompra();
		facturaCompra.setProveedor(proveedor);
		model.put("facturaCompra", facturaCompra);
		model.put("titulo", "Crear Factura");
		
		return "facturaCompra/form";
	}
	
	
	//CARGAR PRODUCTOS --------------------------------------------------------------------
	@GetMapping(value="/cargar-productos/{term}", produces= {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		return proveedorService.findByNombre(term);
	}
	
	
	//GUARDAR -----------------------------------------------------------------------------
	@PostMapping("/form")
	public String guardar(@Valid FacturaCompra facturaCompra, BindingResult result, Model model,
			@RequestParam(name="item_id[]", required=false) Long[] itemId, 
			@RequestParam(name="cantidad[]", required=false) Integer[] cantidad, 
			RedirectAttributes flash, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "facturaCompra/form";
		}
		    
		if(itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Crear Factura");
			model.addAttribute("error", "Error: ¡la factura DEBE tener líneas!");
			return "facturaCompra/form";
		}
		
		for(int i=0; i<itemId.length; i++) {
			Producto producto = proveedorService.findProductoById(itemId[i]);
			
				ItemFCompra item = new ItemFCompra();
				item.setCantidad(cantidad[i]);
				item.setProducto(producto);
				
				facturaCompra.agregarItemFCompra(item);
				producto.getStock().setCantidad(producto.getStock().getCantidad() + cantidad[i]);
		
		}
		
		proveedorService.saveFactura(facturaCompra);
		status.setComplete(); //finalizar la sesión
		
		flash.addFlashAttribute("success", "¡Factura creada con éxito!");
		
		return "redirect:/proveedor/ver/" + facturaCompra.getProveedor().getId();
	}
	
	
	//ELIMINAR ----------------------------------------------------------------------------
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		FacturaCompra facturaCompra = proveedorService.findFacturaById(id);
		
		if(facturaCompra != null) {
			proveedorService.deleteFactura(id);
			flash.addFlashAttribute("success", "Factura eliminada con éxito.");
			return "redirect:/proveedor/ver/" + facturaCompra.getProveedor().getId();
		}
		
		flash.addFlashAttribute("error", "La factura no se puede eliminar porque no existe.");
		return "redirect:/proveedor/listar";
	}

}
