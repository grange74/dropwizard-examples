package com.example.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.config.DynamicLongProperty;
import com.netflix.config.DynamicPropertyFactory;

public class ExternalService {
	private static Logger LOG = LoggerFactory.getLogger(ExternalService.class);

    private static DynamicLongProperty timeToWait = DynamicPropertyFactory
            .getInstance().getLongProperty("externalservice.sleep", 100);

    public static synchronized String justDoIt()
            throws InterruptedException {
    	
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
        
        // simulate doing so long task
        long t = timeToWait.get();
        LOG.info("waiting {} ms", t);
        if (t > 0) {
            Thread.sleep(t);
        }
     
        return "Did it after sleeping " + t + " ms." ;
    }
}
