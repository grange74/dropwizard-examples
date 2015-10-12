package com.example.keycloak;

import io.dropwizard.auth.Auth;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import de.ahus1.keycloak.dropwizard.User;

@Path("/keycloak")
@Produces(MediaType.TEXT_PLAIN)
public class KeycloakResource {
	private final AtomicLong secureCounter;
	private final AtomicLong insecureCounter;

	public KeycloakResource() {
		this.secureCounter = new AtomicLong();
		this.insecureCounter = new AtomicLong();
	}

	@GET
	@Timed
	@Path("secure")
	public String secureMethod(@Auth User user) {

		return "Authenticated access count: " + secureCounter.incrementAndGet() + 
			". Current user: " + user.getName();
	}
	
	@GET
	@Timed
	@Path("insecure")
	public String insecureMethod() {

		return "Free access count: " + insecureCounter.incrementAndGet() + ".";
	}
	
	@GET
	@Timed
	@Path("logout")
	public String logout(@Auth User user) {
		user.logout();
		return "Successfully logged out : " + user.getName();
	}
}
