package com.practice.tomcat;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@Provider
public class JerseyApplication extends ResourceConfig{
	public JerseyApplication() {
		super(JerseyApplication.class);
		register(RolesAllowedDynamicFeature.class);
	}
}
