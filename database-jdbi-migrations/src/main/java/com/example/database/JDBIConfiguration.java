package com.example.database;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JDBIConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    //TODO log an Issue with Dropwizard
    // putting @JsonProperty("database") here instead of above,
    // as per their documentation,
    // gave the following error:
    // your.yml has an error:
    //	  * Unrecognized field at: database
    //	    Did you mean?:
    //	      - metrics
    //	      - server
    //	      - logging
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
