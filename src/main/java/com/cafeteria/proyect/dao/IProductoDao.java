package com.cafeteria.proyect.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cafeteria.proyect.model.Producto;

public interface IProductoDao extends JpaRepository<Producto, Integer>{

	public List<Producto> findByDescripcion(String descripcion);
	public Page<Producto> findByDescripcionLike(String nombre, Pageable pageable);
	public List<Producto> findByDescripcionLike(String nombre);

}
