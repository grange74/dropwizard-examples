package com.example.keycloak;


import io.dropwizard.Configuration;

import org.keycloak.representations.adapters.config.AdapterConfig;

public class KeycloakConfiguration extends Configuration {
	private AdapterConfig keycloakConfiguration = new AdapterConfig();

    public AdapterConfig getKeycloakConfiguration() {
        return keycloakConfiguration;
    }

    public void setKeycloakConfiguration(AdapterConfig keycloakConfiguration) {
        this.keycloakConfiguration = keycloakConfiguration;
    }
}