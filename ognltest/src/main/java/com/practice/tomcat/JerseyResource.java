package com.practice.tomcat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/echo")
public class JerseyResource {
	
	@GET
	public String Echo() {
		return "Hello Jesery !";
		
	}
}
