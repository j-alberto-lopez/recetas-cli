package com.ipartek.controlador;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipartek.componentes.JwtUtil;
import com.ipartek.modelo.Rol;
import com.ipartek.modelo.Usuario;
import com.ipartek.servicio.RolServicio;
import com.ipartek.servicio.UsuarioServicio;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;

@Controller
public class MenuControlador {

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private RolServicio rolServ;
	@Autowired
	private UsuarioServicio usuarioServ;

	@GetMapping("MenuAdmin")
	public String menuAdmin(HttpSession session, Model model) {
		String token = "";
		if ((String) session.getAttribute("s_token") != null) {
			token = (String) session.getAttribute("s_token");

		}

		if (jwtUtil.isTokenValid(token)) {

			Claims claims = jwtUtil.extractClaims(token);

			if (claims.get("rol").equals("ADMIN")) {
				ResponseEntity<?> responseRoles = rolServ.obtenerTodosRoles(token);
				Rol[] rolesArray = (Rol[]) responseRoles.getBody();
				List<Rol> listaRoles = Arrays.asList(rolesArray);

				ResponseEntity<?> responseUsuarios = usuarioServ.obtenerTodosUsuarios(token);
				Usuario[] UsuariosArray = (Usuario[]) responseUsuarios.getBody();
				List<Usuario> listaUsuarios = Arrays.asList(UsuariosArray);

				model.addAttribute("obj_usuario", new Usuario());
				model.addAttribute("listaRoles", listaRoles);
				model.addAttribute("listaUsuarios", listaUsuarios);
				return "admin";
			}
		}

		return "redirect:/";
	}

}
