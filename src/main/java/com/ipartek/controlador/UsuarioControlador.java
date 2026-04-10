package com.ipartek.controlador;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipartek.auxiliar.Auxiliar;
import com.ipartek.componentes.JwtUtil;
import com.ipartek.modelo.Rol;
import com.ipartek.modelo.Usuario;

import com.ipartek.servicio.RolServicio;
import com.ipartek.servicio.UsuarioServicio;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioControlador {

   

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RolServicio rolServ;
	
	@Autowired
	private UsuarioServicio usuarioServ;

	

	@PostMapping("/AgregarUsuario")
	public String agregarUsuario(HttpSession session, Model model, @ModelAttribute Usuario usu) {
		String token = "";
		if ((String) session.getAttribute("s_token") != null) {
			token = (String) session.getAttribute("s_token");
		}

		if (jwtUtil.isTokenValid(token)) {
			Claims claims = jwtUtil.extractClaims(token);

			if (claims.get("rol").equals("ADMIN") ) {
				
				
				System.out.println(usu.getPass());
				System.out.println(usu.getSalt());
				
				String passReal=Auxiliar.hashear(usu.getPass()+usu.getSalt());
				usu.setPass(passReal);
				
				usuarioServ.agregarUsuarios(token, usu);
				
				return "redirect:/MenuAdmin";
			}
		}

		return "redirect:/";
	}

	@GetMapping("/BorrarUsuario")
	public String agregarUsuario(HttpSession session, Model model, @RequestParam Integer id) {
		String token = "";
		if ((String) session.getAttribute("s_token") != null) {
			token = (String) session.getAttribute("s_token");
		}

		if (jwtUtil.isTokenValid(token)) {
			Claims claims = jwtUtil.extractClaims(token);

			if (claims.get("rol").equals("ADMIN") ) {
				
				usuarioServ.borrarUsuarios(token, id);
				
				return "redirect:/MenuAdmin";
			}
		}

		return "redirect:/";
	}

	@GetMapping("/ModificarUsuario")
	public String modificarUsuario(HttpSession session, Model model, @RequestParam Integer id) {
		String token = "";
		if ((String) session.getAttribute("s_token") != null) {
			token = (String) session.getAttribute("s_token");
		}

		if (jwtUtil.isTokenValid(token)) {
			Claims claims = jwtUtil.extractClaims(token);

			if (claims.get("rol").equals("ADMIN") ) {
				
				ResponseEntity<?> responseRoles= rolServ.obtenerTodosRoles(token);
	            Rol[] rolesArray = (Rol[]) responseRoles.getBody();
	            List<Rol> listaRoles = Arrays.asList(rolesArray);
				
				Usuario UsuTemp=(Usuario) usuarioServ.obtenerUsuarioPorId(token, id).getBody();
				
				
				model.addAttribute("listaRoles", listaRoles);
				model.addAttribute("obj_usuario",UsuTemp);
				return "frm_modif_usuario";
			}
		}

		return "redirect:/";
	}

	@PostMapping("/ModificarUsuario")
	public String modificarUsuarioReal(HttpSession session, Model model,  @ModelAttribute Usuario usu) {
		String token = "";
		if ((String) session.getAttribute("s_token") != null) {
			token = (String) session.getAttribute("s_token");
		}

		if (jwtUtil.isTokenValid(token)) {
			Claims claims = jwtUtil.extractClaims(token);

			if (claims.get("rol").equals("ADMIN") || claims.get("rol").equals("USUARIO")) {
				
				Usuario usuTemp=(Usuario) usuarioServ.obtenerUsuarioPorId(token, usu.getId()).getBody();
				
				if (  ! usuTemp.getPass().equalsIgnoreCase(usu.getPass()) ) {
					System.out.println("---------------");
					System.out.println("cambiamos el pass");
					
					String passReal=Auxiliar.hashear(usu.getPass()+usu.getSalt());
					usu.setPass(passReal);
					
					
				}else {
					System.out.println("---------------");
					System.out.println("Mantenemos el pass");
					//podria borrarse el else
					
				}
				
				
				usuarioServ.modificarUsuario(token, usu);
				
				return "redirect:/MenuAdmin";
			}
		}

		return "redirect:/";
	}
}
