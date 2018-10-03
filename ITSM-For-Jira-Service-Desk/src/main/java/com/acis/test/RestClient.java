package com.acis.test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class RestClient {
	public static void main(String[] args) {

		try {
			String URL = "";

			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			client.addFilter(new HTTPBasicAuthFilter("", ""));
			WebResource webResource = client.resource(URL + "/rest/api/2/project/" );
			ClientResponse response = webResource.type("application/json").get(ClientResponse.class);
			if (response.getStatus() == 200)
				System.out.println("response: " + response.getStatus());
			else
				System.out.println("response: " + response.getEntity(String.class));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
