package com.example.keycloak;


import io.dropwizard.Configuration;
import de.ahus1.keycloak.dropwizard.KeycloakConfiguration;

public class KeycloakConfig extends Configuration {
	private KeycloakConfiguration keycloakConfiguration = new KeycloakConfiguration();

    public KeycloakConfiguration getKeycloakConfiguration() {
        return keycloakConfiguration;
    }

    public void setKeycloakConfiguration(KeycloakConfiguration keycloakConfiguration) {
        this.keycloakConfiguration = keycloakConfiguration;
    }
}