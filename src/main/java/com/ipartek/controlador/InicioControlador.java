package com.ipartek.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipartek.excepciones.ApiErrorException;
import com.ipartek.modelo.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class InicioControlador {

	@GetMapping("/")
	public String cargarInicio(HttpSession session, Model model) {

		model.addAttribute("obj_usuario", new Usuario());
		return "login";

	}

}
