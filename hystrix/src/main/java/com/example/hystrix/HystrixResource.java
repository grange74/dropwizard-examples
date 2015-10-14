package com.example.hystrix;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

@Path("/hystrix")
@Produces(MediaType.TEXT_PLAIN)
public class HystrixResource {
	
	public HystrixResource() {
	}

	@GET
	@Timed
	public String callExternalService() {
		return "External Service returned: " + new CommandExternalService().execute();
	}
}