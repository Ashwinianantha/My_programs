package com.acis;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

public class Client {

	public static void main(String[] args) throws Exception {

		String sourceUrl = "http://linux-training.be/linuxfun.pdf";

		String destinationFile = "C:\\test\\linuxfun.pdf";

		saveImage(sourceUrl, destinationFile);
	}

	public static void saveImage(String sourceUrl, String destinationFile) throws IOException {
		URL url = new URL(sourceUrl);
		InputStream inputstream = url.openStream();
		OutputStream outputstream = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = inputstream.read(b)) != -1) {
			outputstream.write(b, 0, length);
		}

		inputstream.close();
		outputstream.close();
	}

}