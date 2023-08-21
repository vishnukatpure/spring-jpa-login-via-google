package com.dev.core.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class SpringRestTemplate {

	@Autowired
	RestTemplate restTemplate;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * 
	 * @param url
	 * @param responseType
	 * @param urlVariables
	 * @return
	 * @throws RestClientException
	 */
	public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> urlVariables)
			throws RestClientException {
		return getRestTemplate().getForEntity(url, responseType, urlVariables);
	}

	/**
	 * 
	 * @param url
	 * @param responseType
	 * @param header
	 * @param urlVariables
	 * @return
	 * @throws RestClientException
	 */
	public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, String> header,
			Map<String, ?> urlVariables) throws RestClientException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAll(header);
		HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);
		return getRestTemplate().exchange(url, HttpMethod.GET, entity, responseType, urlVariables);
	}

	/**
	 * 
	 * @param url
	 * @param header
	 * @param body
	 * @param responseType
	 * @return
	 */
	public <T, K> ResponseEntity<T> post(String url, Map<String, String> header, K body, Class<T> responseType) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAll(header);
		HttpEntity<K> entity = new HttpEntity<K>(body, headers);
		ResponseEntity<T> responseJson = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
		return responseJson;
	}

	/**
	 * 
	 * @param url
	 * @param header
	 * @param body
	 * @param responseType
	 * @return
	 */
	public <T, K> ResponseEntity<T> put(String url, Map<String, String> header, K body, Class<T> responseType) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAll(header);
		HttpEntity<K> entity = new HttpEntity<K>(body, headers);
		ResponseEntity<T> responseJson = restTemplate.exchange(url, HttpMethod.PUT, entity, responseType);
		return responseJson;
	}
}
