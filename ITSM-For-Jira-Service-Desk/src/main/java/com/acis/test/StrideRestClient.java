package com.acis.test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class StrideRestClient {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri("https://api.atlassian.com/oauth/token").build());
		@SuppressWarnings("rawtypes")
		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("client_id", "O8uYvreaDcYFtkAV8u0yXy9yILo3Iv63");
		formData.add("client_secret", "tD5in_NHcJPW3HOgQgXWhrQwWP_ZdxvFK2IUO9BxFVVxzxEDKXbatqoqAxhiLf-c");
		formData.add("grant_type", "client_credentials");
		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(ClientResponse.class, formData);
		
		System.out.println("Status: "+response.getStatus());
		String output = response.getEntity(String.class);
		System.out.println(output);
		try {
			JSONObject _json = new JSONObject(output);
			String accessToken = _json.getString("access_token");
			System.out.println("accessToken: " + accessToken);
		} catch (ClientHandlerException e) {
			e.printStackTrace();
		} catch (UniformInterfaceException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
