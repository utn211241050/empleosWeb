package com.empleos.empleosWeb.service;

import java.util.List;

import com.empleos.empleosWeb.model.Vacante;

public interface IntVacantesService {

	public List<Vacante> obtenerTodas();
	public void agregar(Vacante vacante);
	public void eliminar(int idVacante);
	public Vacante buscarPorId(int idVacante);
	
	
}
