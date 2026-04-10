package com.ipartek.servicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Rol;
import com.ipartek.modelo.Usuario;

public interface RolServicio {
	
	public ResponseEntity<?> obtenerTodosRoles(String token);
	public ResponseEntity<?> obtenerRolPorID(String token,String id);
	

}
