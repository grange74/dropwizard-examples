package com.example.keycloak;

import io.dropwizard.auth.Auth;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import de.ahus1.lottery.adapter.dropwizard.util.Authentication;

@Path("/keycloak")
@Produces(MediaType.TEXT_PLAIN)
public class KeycloakResource {
	private final AtomicLong counter;

	public KeycloakResource() {
		this.counter = new AtomicLong();
	}

	@GET
	@Timed
	public String accessOnlyIfAuthenticated(@Auth Authentication user) {

		return "Authenticated access count: " + counter.incrementAndGet() + 
			". Current user: " + user.getIdToken().getName();
	}
}
