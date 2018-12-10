package com.practice.tomcat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces({"text/plain", "application/json"})
public class JerseyEchoResourceEmbed {
	
	
	
	public JerseyEchoResourceEmbed() {
	}

	@GET
	@Path("{id}")
	public String Echo(@PathParam("id")String id) {
		return "Hello" + id +  "!" ;
		
	}
}
