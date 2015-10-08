package com.example.keycloak;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;

import de.ahus1.keycloak.dropwizardjaxrs.AbstractKeycloakAuthenticator;

public class KeycloakAuthenticator extends AbstractKeycloakAuthenticator<Authentication> {
	@Override
	protected Authentication prepareAuthentication(
			KeycloakSecurityContext securityContext, HttpServletRequest request) {
		return new Authentication(securityContext, request);
	}
}
