package com.example.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observer;

public class HystrixObserver implements Observer<String> {

	private static Logger LOG = LoggerFactory.getLogger(HystrixObserver.class);
	
	public void onCompleted() {
		LOG.info("HystrixObserver onCompleted");
	}

	public void onError(Throwable e) {
		LOG.error("HystrixObserver onError: " + e.getMessage());
	}

	public void onNext(String t) {
		LOG.info("HystrixObserver onNext: " + t);
	}

}
