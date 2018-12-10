package com.practice.tomcat;

import javax.ws.rs.Path;

@Path("/echo")
public class JerseyEchoResource {
	
	@Path("/embed")
	public JerseyEchoResourceEmbed Echo() {
		return new JerseyEchoResourceEmbed();
		
	}
}
