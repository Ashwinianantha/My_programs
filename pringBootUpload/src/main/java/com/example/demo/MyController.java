package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MyController {

	@Value("${upload.path}")
	private String path;

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String upload(@RequestParam MultipartFile file) throws IOException {

		if (!file.isEmpty()) {

			String fileName = file.getOriginalFilename();
			InputStream is = file.getInputStream();

			Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);

			return "redirect:/success.html";

		} else {

			return "redirect:/failure.html";
		}
	}
}
