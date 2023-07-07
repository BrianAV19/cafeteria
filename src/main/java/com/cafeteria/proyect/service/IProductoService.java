package com.cafeteria.proyect.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cafeteria.proyect.model.Producto;

public interface IProductoService {

	public List<Producto> listarProductos(String nombre);
	public Page<Producto> listarPaginado(Pageable pageable);
	public void registrarProducto(Producto producto);
	public Producto buscarPorId(int id);
	public List<Producto> buscarPorDescripcion(String descripcion);
	public Producto actualizarProducto(Producto producto);
	public void eliminarProducto(int id);
	public List<Producto> listarProductos();
}
