package com.example.keycloak;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ForbiddenException;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.IDToken;

import de.ahus1.keycloak.dropwizardjaxrs.AbstractAuthentication;

public class Authentication extends AbstractAuthentication {
	public Authentication(KeycloakSecurityContext securityContext, HttpServletRequest request) {
        super(request, securityContext);
    }

    public void checkUserInRole(Role role) {
        if (!securityContext.getToken().getRealmAccess()
                .isUserInRole(
                        role.name().toLowerCase(Locale.ENGLISH))
                ) {
            throw new ForbiddenException();
        }
    }

    public IDToken getIdToken() {
        return securityContext.getIdToken();
    }
}