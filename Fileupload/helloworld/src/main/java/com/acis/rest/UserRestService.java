package com.acis.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserRestService {

	@GET
	@Path("{id}")
	public Response getUserById(@PathParam("id") String id) {

		return Response.status(200).entity("getUserById is called, id : " + id).build();

	}

	@GET
	@Path("{year}/{month}/{day}")
	public Response getUserHistory(@PathParam("year") int year, @PathParam("month") int month,
			@PathParam("day") int day) {

		String date = year + "/" + month + "/" + day;

		return Response.status(200).entity("date is displayed, year/month/day : " + date).build();
	}
}