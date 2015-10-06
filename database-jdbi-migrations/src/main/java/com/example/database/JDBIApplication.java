package com.example.database;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;

public class JDBIApplication extends Application<JDBIConfiguration> {

	public static void main(String[] args) throws Exception {
		new JDBIApplication().run(args);
	}

	@Override
	public String getName() {
		return "database-jdbi";
	}

	@Override
	public void initialize(Bootstrap<JDBIConfiguration> bootstrap) {
		bootstrap.addBundle(new JDBIMigrationsModule());
	}

	@Override
	public void run(JDBIConfiguration configuration, Environment environment)
			throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment,
				configuration.getDataSourceFactory(), "mariadb");
		final UserDAO dao = jdbi.onDemand(UserDAO.class);
		environment.jersey().register(new UserResource(dao));
	}

}
