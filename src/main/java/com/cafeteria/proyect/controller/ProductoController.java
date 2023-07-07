package com.cafeteria.proyect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafeteria.proyect.model.Producto;
import com.cafeteria.proyect.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired private IProductoService productoService;
	
	@GetMapping("/listar")
	public String index(Model model, @RequestParam(required = false, defaultValue ="0")Integer page) {
		Page<Producto> pg=productoService.listarPaginado(PageRequest.of(page, 5));
		model.addAttribute("producto", pg);
		model.addAttribute("paginacion", "/productos/listar");
		return "productos/index";
	}
	
	@GetMapping("/nuevo")
	public String registrarProducto(Model model) {
		model.addAttribute("producto", new Producto());
		return "productos/registrar";
	}
	
	@PostMapping("/guardar")
	public String guardarProducto(@ModelAttribute("producto") Producto producto) {
		productoService.registrarProducto(producto);
		return "redirect:/productos/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable("id") int id) {
		productoService.eliminarProducto(id);
		System.out.println("Producto eliminado correctamente");
		return "redirect:/productos/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editarProducto(@PathVariable("id") int id,Model model) {
		model.addAttribute("producto", productoService.buscarPorId(id));
		return "productos/editar";
	}
	
	@PostMapping("/actualizar/{id}")
	public String actualizar(@PathVariable("id") int id, @ModelAttribute("producto")Producto producto) {
		Producto proExistente= productoService.buscarPorId(id);
		proExistente.setId(id);
		proExistente.setCategoria(producto.getCategoria());
		proExistente.setProveedor(producto.getProveedor());
		proExistente.setDescripcion(producto.getDescripcion());
		proExistente.setPrecio(producto.getPrecio());
		productoService.actualizarProducto(proExistente);
			
		return "redirect:/productos/listar";
	}
	
	@GetMapping(value = "/productos/{term}", produces = "application/json")
	public @ResponseBody List<Producto> buscarProductosPorNombre(@PathVariable String term) {
	   return productoService.buscarPorDescripcion(term);
	}
	
}
