package com.practice.tomcat;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

/**
 * 前置匹配处理器
 * 即在方法被匹配后执行的过滤器，如果没有匹配到要执行的方法
 * 过滤器不会执行。即如果访问的资源是404过滤器不会执行
 * @author Administrator
 *
 */
@Provider
public class JerseyPostMatchRequestFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("JerseyPostMatchRequestFilter ...");
	}

}
