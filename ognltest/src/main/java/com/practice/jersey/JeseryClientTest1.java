package com.practice.jersey;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.Test;


public class JeseryClientTest1 {

	@Test
	public void test() {
		ClientConfig config = new ClientConfig();
		config.property( ClientProperties.PROXY_URI, "http://0.0.0.0:8888" );
		Client client = ClientBuilder.newClient( config );
		WebTarget webTarget = client.target("http://www.baidu.com");
		String res = webTarget.request().get(String.class);
		System.out.println(res);
		
	}

}
