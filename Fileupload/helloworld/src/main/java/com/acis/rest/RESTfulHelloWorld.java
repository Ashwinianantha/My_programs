package com.acis.rest;

import java.util.Date;
import java.util.List;

import javax.sound.midi.Track;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/asdsd")
public class RESTfulHelloWorld {
	@GET
	@Produces("text/html")
	public Response getStartingPage() {

		String output = "<h1>Hello World!<h1>" + "<p>RESTful Service is running ... <br>Ping @ " + new Date().toString()
				+ "</p<br>";
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postStartingPage() {

		String result = "kfjkjthkfjhkjdh";
		return Response.status(201).entity(result).build();

	}

	@GET
	@Path("/query")
	public Response getUsers(@QueryParam("from") int from, @QueryParam("to") int to,
			@QueryParam("orderBy") List<String> orderBy) {

		return Response.status(200)
				.entity("getUsers is called, from : " + from + ", to : " + to + ", orderBy" + orderBy.toString())
				.build();

	}

	
}
