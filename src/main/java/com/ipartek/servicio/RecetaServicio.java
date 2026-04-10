package com.ipartek.servicio;

import java.util.List;
import com.ipartek.modelo.Receta;

public interface RecetaServicio {

	List<Receta> obtenerTodas();

	Receta obtenerPorId(int id);

	Receta guardar(Receta receta);

	void borrar(int id);
	
	Receta modificar(Receta receta);
}