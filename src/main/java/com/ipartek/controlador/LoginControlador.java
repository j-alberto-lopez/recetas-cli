package com.ipartek.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipartek.excepciones.ApiErrorException;
import com.ipartek.modelo.MsgError;
import com.ipartek.modelo.Usuario;
import com.ipartek.servicio.RolServicio;
import com.ipartek.servicio.UsuarioServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginControlador {

	@Autowired
	private UsuarioServicio usuarioServ;

	@PostMapping("/ValidarUsuario")
	public String validarUsuario(HttpSession session, Model model, @ModelAttribute Usuario obj_usuario) {
		try {
			ResponseEntity<?> re = usuarioServ.validarUsuario(obj_usuario);

			if (re.getBody() instanceof String) {
				String token = re.getBody().toString();

				session.setAttribute("s_token", token);

				return "redirect:/recetas";
			}

			return "redirect:/";
		} catch (ApiErrorException e) {
			model.addAttribute("error", e.getMensaje());
			model.addAttribute("codigoError", e.getCodigo());
			return "/error/500";

		}
	}

	@GetMapping("/CerrarSesion")
	public String cerrarSesion(HttpSession session, Model model) {
		try {
			session.invalidate();

			return "redirect:/";
		} catch (ApiErrorException e) {
			model.addAttribute("error", e.getMensaje());
			model.addAttribute("codigoError", e.getCodigo());
			return "/error/500";

		}
	}

}
