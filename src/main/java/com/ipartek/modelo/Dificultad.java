package com.ipartek.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;




public class Dificultad {

	
	private int id;

	
	private String nombre;


	private List<Receta> recetas;

	public Dificultad(int id, String nombre, List<Receta> recetas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.recetas = recetas;
	}

	public Dificultad() {
		super();
		this.id = 0;
		this.nombre = "";
		this.recetas = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	@Override
	public String toString() {
		return "Dificultad [id=" + id + ", nombre=" + nombre + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Dificultad other = (Dificultad) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

}
