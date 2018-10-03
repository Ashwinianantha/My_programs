package com.acis.itsm.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import com.acis.itsm.utils.Constants;
import com.atlassian.jira.util.json.JSONException;
import com.atlassian.jira.util.json.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class StrideClient {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String getAccessToken() {
		String accessToken = null;
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource webResource = client.resource(UriBuilder.fromUri(Constants.token_url).build());

		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("client_id", Constants.client_id);
		formData.add("client_secret", Constants.client_secret);
		formData.add("grant_type", "client_credentials");
		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
				.post(ClientResponse.class, formData);
		int statuCode = response.getStatus();
		// System.out.println("Status: " + statuCode);
		if (statuCode == 200) {
			String output = response.getEntity(String.class);
			System.out.println(output);
			try {
				JSONObject _json = new JSONObject(output);
				accessToken = _json.getString("access_token");
				// System.out.println("accessToken: " + accessToken);
			} catch (ClientHandlerException e) {
				e.printStackTrace();
			} catch (UniformInterfaceException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return accessToken;

	}

	public static String createConverstion(String cloudId, String name, String topic) {
		String token = getAccessToken();
		String conversationId = null;
		if (token != null) {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);

			String url = Constants.stride_url + "/site/" + cloudId + "/conversation";
			String input_data = "{\"name\":\"" + name + "\", \"privacy\":\"public\", \"topic\":\"" + topic + "\"}";

			WebResource webResource = client.resource(UriBuilder.fromUri(url).build());
			ClientResponse response = webResource.header("Authorization", "Bearer " + token)
					.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input_data);
			int statuCode = response.getStatus();
			System.out.println("Status: " + statuCode);
			if (statuCode == 201) {
				String output = response.getEntity(String.class);
				try {
					JSONObject _json = new JSONObject(output);
					conversationId = _json.getString("id");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				System.out.println(output);
			}
		}

		return conversationId;

	}

	public static void updateConversationRoster(String cloudId, String conversationId) {
		String token = getAccessToken();
		if (token != null) {
			ClientConfig config = new DefaultClientConfig();
			config.getProperties().put(URLConnectionClientHandler.PROPERTY_HTTP_URL_CONNECTION_SET_METHOD_WORKAROUND,
					true);
			Client client = Client.create(config);

			String url = Constants.stride_url + "/site/" + cloudId + "/conversation/" + conversationId + "/roster";
			String input_data = "{\"add\": [\"admin\", \"jordan\"]}";

			WebResource webResource = client.resource(UriBuilder.fromUri(url).build());
			ClientResponse response = webResource.header("Authorization", "Bearer " + token)
					.type(MediaType.APPLICATION_JSON).method("PATCH", ClientResponse.class, input_data);
			int statuCode = response.getStatus();
			System.out.println("Status: " + statuCode);
			if (statuCode == 200) {
				String output = response.getEntity(String.class);
				System.out.println(output);
				/*
				 * try { JSONObject _json = new JSONObject(output); conversationDetails =
				 * _json.getString("id"); } catch (JSONException e) { e.printStackTrace(); }
				 */

			}
		}
	}

	public static String getConversation(String cloudId, String conversationId) {

		String token = getAccessToken();
		String conversationDetails = null;
		if (token != null) {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);

			String url = Constants.stride_url + "/site/" + cloudId + "/conversation/" + conversationId;
			// String input_data = "{\"name\":\"Rest_Conversation_Java\",
			// \"privacy\":\"public\", \"topic\":\"Created Using Java Rest Client\"}";

			WebResource webResource = client.resource(UriBuilder.fromUri(url).build());
			ClientResponse response = webResource.header("Authorization", "Bearer " + token)
					.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
			int statuCode = response.getStatus();
			System.out.println("Status: " + statuCode);
			if (statuCode == 200) {
				String output = response.getEntity(String.class);
				System.out.println(output);
				/*
				 * try { JSONObject _json = new JSONObject(output); conversationDetails =
				 * _json.getString("id"); } catch (JSONException e) { e.printStackTrace(); }
				 */

			}
		}

		return conversationDetails;

	}

	public static void main(String[] args) {
		// System.out.println(createConverstion(Constants.cloud_id));
		// getConversation(Constants.cloud_id, "e1c84534-0007-43cd-8eb2-9da59f92aa31");
//		updateConversationRoster(Constants.cloud_id, "e1c84534-0007-43cd-8eb2-9da59f92aa31");

	}
}
