package com.practice.tomcat;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

/**
 * 前置匹配过滤器，在方法匹配前执行的过滤器，
 * 无论能不能匹配到方法都会执行，即使请求
 * 是404也会执行
 * @author Administrator
 *
 */
@PreMatching
@Provider
public class JerseyPreMatchRequestFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("JerseyPreMatchRequestFilter ...");
	}

}
