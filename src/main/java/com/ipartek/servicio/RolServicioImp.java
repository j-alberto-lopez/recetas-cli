package com.ipartek.servicio;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipartek.excepciones.ApiErrorException;
import com.ipartek.modelo.MsgError;
import com.ipartek.modelo.Rol;
import com.ipartek.modelo.Usuario;

@Service
public class RolServicioImp implements RolServicio {

	private RestTemplate restTemp = new RestTemplate();
	private String URL = "http://localhost:9090/api/v1/roles/";

	@Override
	public ResponseEntity<?> obtenerTodosRoles(String token) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(token);

			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<?> re = restTemp.exchange(URL, HttpMethod.GET, entity, Rol[].class);
			return re;
		} catch (HttpServerErrorException e) {
			String responseBody = e.getResponseBodyAsString();

			try {
				ObjectMapper mapper = new ObjectMapper();
				MsgError error = mapper.readValue(responseBody, MsgError.class);

				throw new ApiErrorException(error);

			} catch (JsonProcessingException ex) {
				throw new RuntimeException("Error al obtener lista de roles: " + responseBody);
			}
		} catch (RestClientException e) {
			throw new RuntimeException("Error de comunicación con la API", e);
		}
	}

	@Override
	public ResponseEntity<?> obtenerRolPorID(String token, String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
