package com.example.hystrix;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.zapodot.hystrix.bundle.HystrixBundle;

public class HystrixApplication extends Application<HystrixConfiguration> {
	public static void main(String[] args) throws Exception {
		new HystrixApplication().run(args);
	}

	@Override
	public String getName() {
		return "hystrix";
	}

	@Override
	public void initialize(Bootstrap<HystrixConfiguration> bootstrap) {
		bootstrap.addBundle(HystrixBundle.withDefaultSettings());
	}

	@Override
	public void run(HystrixConfiguration configuration,
			Environment environment) {
		
		final HystrixResource resource = new HystrixResource();
				
		environment.jersey().register(resource);
	}

}
