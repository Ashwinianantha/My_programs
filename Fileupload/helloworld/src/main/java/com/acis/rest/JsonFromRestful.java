package com.acis.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

@Path("/customers")
public class JsonFromRestful {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)

	public Response produceCustomerDetailsinJSON(@PathParam("cusNo") int no) {

		Customer cust = new Customer();
		cust.setCustNo(no);
		cust.setCustName("Java4s");
		cust.setCustCountry("India");

		String jsonInString = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonInString = mapper.writeValueAsString(cust);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(jsonInString).build();
	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createCustomerInJSON(Customer customer) {

		String result = "Customer created : " + customer;
		return Response.status(201).entity(result).build();

	}
}
