package com.practice.tomcat;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Resource
public class JerseyResource {
	
	
	@GET
	@Path("/echo")
	public String Echo() {
		return "Hello Jesery !";
		
	}
}
