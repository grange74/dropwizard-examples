package com.example.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class ExternalServiceCommand extends HystrixCommand<String> {
	private static Logger LOG = LoggerFactory.getLogger(ExternalServiceCommand.class);
	
	public ExternalServiceCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
    }

    @Override
    protected String run() {
        try {
			return ExternalService.justDoIt();
		} catch (InterruptedException e) {
			LOG.error("The justDoIt method just throw an InterruptedException method.");
		}
		return "Couldn't do it";
    }
    
    @Override
	protected String getFallback() {
		return "Have to fallback";
	}
}
