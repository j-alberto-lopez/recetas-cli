package com.ipartek.servicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Usuario;

public interface UsuarioServicio {
	
	
	
	public ResponseEntity<?> validarUsuario(Usuario usu);

	public ResponseEntity<?> obtenerTodosUsuarios(String token);
	
	public ResponseEntity<?> agregarUsuarios(String token,Usuario usu);

	public ResponseEntity<?> borrarUsuarios(String token, Integer id);

	public ResponseEntity<?> obtenerUsuarioPorId(String token, Integer id);

	public ResponseEntity<?> modificarUsuario(String token, Usuario usu);

}
