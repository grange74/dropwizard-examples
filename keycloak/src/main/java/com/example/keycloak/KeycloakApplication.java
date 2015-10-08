package com.example.keycloak;


import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.jersey.sessions.HttpSessionFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.keycloak.adapters.jetty.KeycloakJettyAuthenticator;

import de.ahus1.lottery.adapter.dropwizard.util.Authentication;
import de.ahus1.lottery.adapter.dropwizard.util.KeycloakAuthFactory;
import de.ahus1.lottery.adapter.dropwizard.util.KeycloakAuthenticator;
import de.ahus1.lottery.adapter.dropwizard.util.KeycloakDropwizardAuthenticator;

public class KeycloakApplication extends Application<KeycloakConfiguration> {
	public static void main(String[] args) throws Exception {
		new KeycloakApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<KeycloakConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(KeycloakConfiguration configuration,
			Environment environment) {
        KeycloakJettyAuthenticator keycloak = new KeycloakDropwizardAuthenticator();
        keycloak.setAdapterConfig(configuration.getKeycloakConfiguration());
        ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
        environment.getApplicationContext().setSecurityHandler(securityHandler);
        environment.getApplicationContext().getSecurityHandler().setAuthenticator(keycloak);

        KeycloakAuthFactory authFactory = new KeycloakAuthFactory(configuration.getKeycloakConfiguration(), "dropwizard", new KeycloakAuthenticator(), Authentication.class);
        environment.jersey().register(AuthFactory.binder(authFactory));

        // allow (stateful) sessions in Dropwizard
        environment.jersey().register(HttpSessionFactory.class);
        environment.servlets().setSessionHandler(new SessionHandler());

        // register web resources.
        environment.jersey().register(new KeycloakResource());

        // support annotation @RolesAllowed
        environment.jersey().register(RolesAllowedDynamicFeature.class);
	}
}
