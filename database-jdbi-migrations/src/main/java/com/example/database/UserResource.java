package com.example.database;

import io.dropwizard.jersey.params.IntParam;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private UserDAO dao;

	public UserResource(UserDAO dao) {
		this.dao = dao;
	}

	@GET
	@Timed
	public User getUser(@QueryParam("id") IntParam id) {
		String name = dao.findNameById(id.get());
		return new User(id.get(), name);
	}

	@POST
	@Timed
	public Response createUser(@Valid User user) {	
		dao.insert(user.getId(), user.getName());
		return Response.ok().build();
	}
}
