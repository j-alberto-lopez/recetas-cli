package com.ipartek.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Receta;

@Service
public class RecetaServicioImp {

	private final String URL = "http://localhost:9090/api/v1/recetas";

	private RestTemplate restTemplate = new RestTemplate();

	
	public List<Receta> obtenerTodas() {

		ResponseEntity<Receta[]> response = restTemplate.exchange(URL, HttpMethod.GET, null, Receta[].class);

		return Arrays.asList(response.getBody());
	}

	
	public Receta obtenerPorId(int id) {

		ResponseEntity<Receta> response = restTemplate.exchange(URL + "/" + id, HttpMethod.GET, null, Receta.class);

		return response.getBody();
	}

	
	public Receta guardar(Receta receta) {

		HttpEntity<Receta> request = new HttpEntity<>(receta);

		ResponseEntity<Receta> response = restTemplate.exchange(URL, HttpMethod.POST, request, Receta.class);

		return response.getBody();
	}

	
	public Receta modificar(int id, Receta receta) {

		HttpEntity<Receta> request = new HttpEntity<>(receta);

		ResponseEntity<Receta> response = restTemplate.exchange(URL + "/" + id, HttpMethod.PUT, request, Receta.class);

		return response.getBody();
	}

	
	public void borrar(int id) {

		restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, null, Void.class);
	}
}