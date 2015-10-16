package com.example.hystrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rx.Observable;

import com.codahale.metrics.annotation.Timed;

@Path("/hystrix")
@Produces(MediaType.TEXT_PLAIN)
public class HystrixResource {
	private static Logger LOG = LoggerFactory.getLogger(HystrixResource.class);
	
	public HystrixResource() {
	}

	@GET
	@Timed
	@Path("execute")
	public String execute() {
		return "External Service returned: " + new ExternalServiceCommand().execute();
	}
	
	@GET
	@Timed
	@Path("queue")
	public String queue() {
		
		ExternalServiceCommand command = new ExternalServiceCommand();
		
		Future<String> future = command.queue();
		
		// doing some other stuff
		LOG.info("Request has been queued");
		
		try 
		{
			// block until we get a response
			return "External Service returned: " + future.get();
		} 
		catch (InterruptedException e) {
			return "InterruptedException";
		} catch (ExecutionException e) {
			return "ExecutionException";
		}
	}
	
	@GET
	@Timed
	@Path("observe")
	public String observe() {
		
		ExternalServiceCommand command = new ExternalServiceCommand();
		
		Observable<String> observable = command.observe();
		
		LOG.info("Request has been queued");
		
		// doing some other stuff
		
		observable.subscribe(new HystrixObserver());
		
		return "Your request is being Observed";
	}
}