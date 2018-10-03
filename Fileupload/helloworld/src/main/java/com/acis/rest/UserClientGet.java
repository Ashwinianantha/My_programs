package com.acis.rest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserClientGet {
	public static void main(String[] args) {

		Client client = Client.create();

		WebResource webResource = client.resource("http://localhost:9900/helloworld/rest/users/2018/08/21");

		ClientResponse response = webResource.accept("text/html").get(ClientResponse.class);

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);

	}

}
