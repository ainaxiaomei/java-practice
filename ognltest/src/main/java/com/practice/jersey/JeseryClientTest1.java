package com.practice.jersey;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class JeseryClientTest1 {

	@Test
	public void test() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://www.baidu.com");
		String res = webTarget.request().get(String.class);
		System.out.println(res);
		
	}

}
