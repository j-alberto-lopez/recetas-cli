package com.ipartek.modelo;

import java.util.Objects;

public class Receta {

	
	private int id;
	

	private String nombre;
	
	
	private Dificultad dificultad;

	public Receta(int id, String nombre, Dificultad dificultad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dificultad = dificultad;
	}
	
	public Receta() {
		super();
		this.id = 0;
		this.nombre = "";
		this.dificultad = new Dificultad();
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

	public Dificultad getDificultad() {
		return dificultad;
	}

	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}

	@Override
	public String toString() {
		return "Receta [id=" + id + ", nombre=" + nombre + ", dificultad=" + dificultad + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dificultad, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receta other = (Receta) obj;
		return Objects.equals(dificultad, other.dificultad) && id == other.id && Objects.equals(nombre, other.nombre);
	}
	
}
