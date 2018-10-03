package com.acis;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.acis.model.User;

public class RestClient {

	public static void main(String[] args) {
		createUser();

	}

	/* POST */
	private static void createUser() {
		System.out.println("Testing create User API----------");
		RestTemplate restTemplate = new RestTemplate();
		User user = new User(6, "ashra", 59, 234);
		URI uri = restTemplate.postForLocation("http://localhost:4040/api/user/", user, User.class);
		System.out.println("Location : " + uri.toASCIIString());
	}

}
