package com.example.keycloak;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import de.ahus1.keycloak.dropwizard.KeycloakBundle;
import de.ahus1.keycloak.dropwizard.KeycloakConfiguration;

public class KeycloakApplication extends Application<KeycloakConfig> {
	public static void main(String[] args) throws Exception {
		new KeycloakApplication().run(args);
	}

	@Override
	public String getName() {
		return "keycloak";
	}

	@Override
	public void initialize(Bootstrap<KeycloakConfig> bootstrap) {
		bootstrap.addBundle(new KeycloakBundle<KeycloakConfig>() {
            @Override
            protected KeycloakConfiguration getKeycloakConfiguration(KeycloakConfig configuration) {
                return configuration.getKeycloakConfiguration();
            }
        });
	}

	@Override
	public void run(KeycloakConfig configuration, Environment environment) {
		
        // register web resources.
        environment.jersey().register(new KeycloakResource());

        // support annotation @RolesAllowed
        environment.jersey().register(RolesAllowedDynamicFeature.class);
	}
}
