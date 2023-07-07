package com.cafeteria.proyect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cafeteria.proyect.dao.IProductoDao;
import com.cafeteria.proyect.model.Producto;

@Service
public class ProductoService implements IProductoService{
	
	@Autowired private IProductoDao productoDao;

	@Override
	public List<Producto> listarProductos(String descripcion) {
		if(descripcion != null) {
			return productoDao.findByDescripcion(descripcion);
		}
		return productoDao.findAll();
	}

	@Override
	public Page<Producto> listarPaginado(Pageable pageable) {
		return productoDao.findAll(pageable);
	}

	@Override
	public void registrarProducto(Producto producto) {
		productoDao.save(producto);
	}

	@Override
	public Producto buscarPorId(int id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	public Producto actualizarProducto(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	public void eliminarProducto(int id) {
		productoDao.deleteById(id);
	}

	@Override
	public List<Producto> listarProductos() {
		return productoDao.findAll();
	}

	@Override
	public List<Producto> buscarPorDescripcion(String descripcion) {
		return productoDao.findByDescripcionLike(descripcion);
	}

}
