package com.ipartek.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ipartek.modelo.Dificultad;
import com.ipartek.servicio.DificultadServicioImp;

@Controller
@RequestMapping("/dificultades")
public class DificultadControlador {

	@Autowired
	private DificultadServicioImp servicio;

	@GetMapping
	public String listar(Model model) {

		List<Dificultad> dificultades = servicio.obtenerTodas();

		model.addAttribute("dificultades", dificultades);

		return "dificultades";
	}

	@PostMapping
	public String guardar(Dificultad dificultad) {

		servicio.guardar(dificultad);

		return "redirect:/dificultades";
	}

	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable int id) {

		servicio.borrar(id);

		return "redirect:/dificultades";
	}
}