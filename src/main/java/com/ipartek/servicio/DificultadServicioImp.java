package com.ipartek.servicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ipartek.modelo.Dificultad;

@Service
public class DificultadServicioImp {

	private final String URL = "http://localhost:9090/api/v1/dificultades";

	private RestTemplate restTemplate = new RestTemplate();

	public List<Dificultad> obtenerTodas() {

		ResponseEntity<Dificultad[]> response = restTemplate.exchange(URL, HttpMethod.GET, null, Dificultad[].class);

		return Arrays.asList(response.getBody());
	}

	public Dificultad obtenerPorId(int id) {

		ResponseEntity<Dificultad> response = restTemplate.exchange(URL + "/" + id, HttpMethod.GET, null,
				Dificultad.class);

		return response.getBody();
	}

	public Dificultad guardar(Dificultad dificultad) {

		HttpEntity<Dificultad> request = new HttpEntity<>(dificultad);

		ResponseEntity<Dificultad> response = restTemplate.exchange(URL, HttpMethod.POST, request, Dificultad.class);

		return response.getBody();
	}

	public Dificultad modificar(int id, Dificultad dificultad) {

		HttpEntity<Dificultad> request = new HttpEntity<>(dificultad);

		ResponseEntity<Dificultad> response = restTemplate.exchange(URL + "/" + id, HttpMethod.PUT, request,
				Dificultad.class);

		return response.getBody();
	}

	public void borrar(int id) {

		restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, null, Void.class);
	}
}