package com.example.keycloak;


import de.ahus1.keycloak.dropwizard.KeycloakConfiguration;
import io.dropwizard.Configuration;

public class KeycloakConfig extends Configuration {
	private KeycloakConfiguration keycloakConfiguration = new KeycloakConfiguration();

    public KeycloakConfiguration getKeycloakConfiguration() {
        return keycloakConfiguration;
    }

    public void setKeycloakConfiguration(KeycloakConfiguration keycloakConfiguration) {
        this.keycloakConfiguration = keycloakConfiguration;
    }
}