package com.acis.Client;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	@Autowired
	private RestTemplateBuilder restTemplate;

	public void downloadFile() { // This method will download file using RestTemplate
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			ResponseEntity<byte[]> response = restTemplate.build().exchange("http://localhost:9000/download/file",
					HttpMethod.GET, entity, byte[].class);
			Files.write(Paths.get("C:\\test\\upload\\linuxcommands"), response.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
