package com.ipartek.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ipartek.modelo.Receta;
import com.ipartek.servicio.RecetaServicioImp;
import com.ipartek.servicio.DificultadServicioImp;

@Controller
@RequestMapping("/recetas")
public class RecetaControlador {

	@Autowired
	private RecetaServicioImp recetaServicio;

	@Autowired
	private DificultadServicioImp dificultadServicio;

	@GetMapping
	public String listar(Model model) {

		List<Receta> recetas = recetaServicio.obtenerTodas();

		model.addAttribute("recetas", recetas);
		model.addAttribute("dificultades", dificultadServicio.obtenerTodas());
		model.addAttribute("receta", new Receta());

		return "recetas";
	}

	@PostMapping
	public String guardar(Receta receta) {

		recetaServicio.guardar(receta);

		return "redirect:/recetas";
	}

	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable int id) {

		recetaServicio.borrar(id);

		return "redirect:/recetas";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model) {

		model.addAttribute("recetas", recetaServicio.obtenerTodas());
		model.addAttribute("dificultades", dificultadServicio.obtenerTodas());

		model.addAttribute("receta", recetaServicio.obtenerPorId(id)); // 👈 clave

		return "recetas";
	}
}