package com.practice.tomcat;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;


/**
 * 名称绑定的过滤器只对指定注解的资源生效，没有指定注解的资源不会被拦截
 * 如果名称绑定的过滤器指定了多个注解则资源必须包含所有注解才会被拦截
 * @author Administrator
 *
 */

@Provider
@Async
public class JeseryNameBindFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("JeseryNameBindFilter : Async");
	}
	
}
