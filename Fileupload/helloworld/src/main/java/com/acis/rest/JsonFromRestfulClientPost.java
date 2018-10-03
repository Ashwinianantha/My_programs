package com.acis.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.core.HttpResponseContext;

public class JsonFromRestfulClientPost {
	public static void main(String[] args) {

		Client client = new Client();
		WebResource webResource = client.resource("http://localhost:9900/helloworld/rest/customers/100");

		String input = new String("{\"custName\":\"sjdh");

		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

		String output = response.getEntity(String.class);
		System.out.println("Output from Server .... \n");
		System.out.println(output);
	}

}
