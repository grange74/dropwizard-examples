package com.example.database;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;

public class JDBIMigrationsModule extends MigrationsBundle<JDBIConfiguration>
{
	public DataSourceFactory getDataSourceFactory(
			JDBIConfiguration configuration) {
		return configuration.getDataSourceFactory();
	}
}
