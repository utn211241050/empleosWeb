package com.empleos.empleosWeb.service;

import java.util.List;

import com.empleos.empleosWeb.model.Categoria;

public interface IntCategoriasService {

	
	public List<Categoria> obtenerTodas();
	public void eliminar(Integer idCategoria);
	public void guardar(Categoria categoria);
	public Categoria buscarPorId(Integer idcategoria);
	
}
