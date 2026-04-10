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
import com.ipartek.modelo.Usuario;

@Service
public class UsuarioServicioImp implements UsuarioServicio {

	private RestTemplate restTemp = new RestTemplate();
	private String URL = "http://localhost:9090/api/v1/usuarios/";

	@Override
	public ResponseEntity<?> validarUsuario(Usuario usu) {
		try {
			HttpHeaders headers = new HttpHeaders();

			HttpEntity<Usuario> entity = new HttpEntity<Usuario>(usu, headers);

			ResponseEntity<?> re = restTemp.exchange(URL + "ValidarUsuario", HttpMethod.POST, entity, String.class);
			return re;
		} catch (HttpServerErrorException e) {
			String responseBody = e.getResponseBodyAsString();

			try {
				ObjectMapper mapper = new ObjectMapper();
				MsgError error = mapper.readValue(responseBody, MsgError.class);

				throw new ApiErrorException(error);

			} catch (JsonProcessingException ex) {
				throw new RuntimeException("Error al validar usuario: " + responseBody);
			}
		} catch (RestClientException e) {
			throw new RuntimeException("Error de comunicación con la API", e);
		}

	}

	@Override
	public ResponseEntity<?> obtenerTodosUsuarios(String token) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(token);

			HttpEntity<Usuario[]> entity = new HttpEntity<>(headers);

			ResponseEntity<?> re = restTemp.exchange(URL, HttpMethod.GET, entity, Usuario[].class);

			return re;
		} catch (HttpServerErrorException e) {
			String responseBody = e.getResponseBodyAsString();

			try {
				ObjectMapper mapper = new ObjectMapper();
				MsgError error = mapper.readValue(responseBody, MsgError.class);

				throw new ApiErrorException(error);

			} catch (JsonProcessingException ex) {
				throw new RuntimeException("Error al obtener lista de usuarios: " + responseBody);
			}
		} catch (RestClientException e) {
			throw new RuntimeException("Error de comunicación con la API", e);
		}
	}

	@Override
	public ResponseEntity<?> agregarUsuarios(String token, Usuario usu) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(token);

			HttpEntity<Usuario> entity = new HttpEntity<>(usu, headers);

			ResponseEntity<?> re = restTemp.exchange(URL, HttpMethod.POST, entity, Usuario.class);

			return re;
		} catch (HttpServerErrorException e) {
			String responseBody = e.getResponseBodyAsString();

			try {
				ObjectMapper mapper = new ObjectMapper();
				MsgError error = mapper.readValue(responseBody, MsgError.class);

				throw new ApiErrorException(error);

			} catch (JsonProcessingException ex) {
				throw new RuntimeException("Error al obtener lista de usuarios: " + responseBody);
			}
		} catch (RestClientException e) {
			throw new RuntimeException("Error de comunicación con la API", e);
		}
	}

	@Override
	public ResponseEntity<?> borrarUsuarios(String token, Integer id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(token);

			HttpEntity<Usuario> entity = new HttpEntity<>(headers);

			ResponseEntity<?> re = restTemp.exchange(URL + id, HttpMethod.DELETE, entity, Boolean.class);

			return re;
		} catch (HttpServerErrorException e) {
			String responseBody = e.getResponseBodyAsString();

			try {
				ObjectMapper mapper = new ObjectMapper();
				MsgError error = mapper.readValue(responseBody, MsgError.class);

				throw new ApiErrorException(error);

			} catch (JsonProcessingException ex) {
				throw new RuntimeException("Error al obtener lista de usuarios: " + responseBody);
			}
		} catch (RestClientException e) {
			throw new RuntimeException("Error de comunicación con la API", e);
		}
	}

	@Override
	public ResponseEntity<?> obtenerUsuarioPorId(String token, Integer id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(token);

			HttpEntity<Usuario> entity = new HttpEntity<>(headers);

			ResponseEntity<?> re = restTemp.exchange(URL + id, HttpMethod.GET, entity, Usuario.class);

			return re;
		} catch (HttpServerErrorException e) {
			String responseBody = e.getResponseBodyAsString();

			try {
				ObjectMapper mapper = new ObjectMapper();
				MsgError error = mapper.readValue(responseBody, MsgError.class);

				throw new ApiErrorException(error);

			} catch (JsonProcessingException ex) {
				throw new RuntimeException("Error al obtener lista de usuarios: " + responseBody);
			}
		} catch (RestClientException e) {
			throw new RuntimeException("Error de comunicación con la API", e);
		}
	}

	@Override
	public ResponseEntity<?> modificarUsuario(String token, Usuario usu) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(token);

			HttpEntity<Usuario> entity = new HttpEntity<>(usu, headers);

			ResponseEntity<?> re = restTemp.exchange(URL, HttpMethod.PUT, entity, Usuario.class);

			return re;
		} catch (HttpServerErrorException e) {
			String responseBody = e.getResponseBodyAsString();

			try {
				ObjectMapper mapper = new ObjectMapper();
				MsgError error = mapper.readValue(responseBody, MsgError.class);

				throw new ApiErrorException(error);

			} catch (JsonProcessingException ex) {
				throw new RuntimeException("Error al obtener lista de usuarios: " + responseBody);
			}
		} catch (RestClientException e) {
			throw new RuntimeException("Error de comunicación con la API", e);
		}
	}

}
