package com.example.keycloak;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.keycloak.representations.adapters.config.AdapterConfig;

import de.ahus1.keycloak.dropwizard.KeycloakBundle;

public class KeycloakApplication extends Application<KeycloakConfiguration> {
	public static void main(String[] args) throws Exception {
		new KeycloakApplication().run(args);
	}

	@Override
	public String getName() {
		return "keycloak";
	}

	@Override
	public void initialize(Bootstrap<KeycloakConfiguration> bootstrap) {
		bootstrap.addBundle(new KeycloakBundle<KeycloakConfiguration>() {
            @Override
            protected AdapterConfig getKeycloakConfiguration(KeycloakConfiguration configuration) {
                return configuration.getKeycloakConfiguration();
            }
        });
	}

	@Override
	public void run(KeycloakConfiguration configuration,
			Environment environment) {
        // register web resources.
        environment.jersey().register(new KeycloakResource());

        // support annotation @RolesAllowed
        environment.jersey().register(RolesAllowedDynamicFeature.class);
	}
}
