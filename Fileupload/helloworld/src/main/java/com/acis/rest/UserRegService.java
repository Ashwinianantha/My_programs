package com.acis.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/user-form")
public class UserRegService {

	@POST
	@Path("/register")
	public Response registerUserInfo(@FormParam("name") String name, @FormParam("address") String address,
			@FormParam("gender") String Name) {

		String response = "Successfully added user details, name: " + name + " and address: " + address + "       gender:"
				+ Name;
		return Response.status(200).entity(response).build();
	}
}