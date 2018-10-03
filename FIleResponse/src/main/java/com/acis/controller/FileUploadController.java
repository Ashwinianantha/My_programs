package com.acis.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class FileUploadController {

	private static final String EXTERNAL_FILE_PATH = "C:\\test\\upload\\";

	@RequestMapping("/file/{fileName:.+}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {

		File file = new File(EXTERNAL_FILE_PATH + fileName);
		if (file.exists()) {

		
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
			
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			 response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			//response.setHeader("Content-Disposition", String.format("attachment;filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}
	}
}
