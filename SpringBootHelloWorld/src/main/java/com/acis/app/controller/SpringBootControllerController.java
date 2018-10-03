package com.acis.app.controller;

import org.springframework.http.MediaType;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootControllerController {
	@RequestMapping(value = "/sid", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> getImage() throws IOException {

		ClassPathResource imgFile = new ClassPathResource("images/tree.jpg/tree.jpg");

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.body(new InputStreamResource(imgFile.getInputStream()));
	}

	@RequestMapping(value = "/sid1", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<InputStreamResource> getImag() throws IOException {

		ClassPathResource imgFile = new ClassPathResource("images/tree.jpg/flower.jpg");

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.body(new InputStreamResource(imgFile.getInputStream()));
	}
}