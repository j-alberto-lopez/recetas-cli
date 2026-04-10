package com.ipartek.servicio;

import java.util.List;

import com.ipartek.modelo.Dificultad;

public interface DificultadServicio {
	
	List<Dificultad> obtenerTodas();
	
	Dificultad obtenerPorId(int id);
	
	Dificultad guardar(Dificultad dificultad);
	
	void borrar(int id);

}
