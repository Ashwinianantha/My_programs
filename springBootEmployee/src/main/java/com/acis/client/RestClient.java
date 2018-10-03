package com.acis.client;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://localhost:7080/", String.class, 200);
		System.out.println("result" + result);
		
	}

}
