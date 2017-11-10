package com.practice.tomcat;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 * 权限验证过滤器，将用户角色放入securityContext
 * 从而通过javaee 的安全认证机制.
 * 主要是isUserInRole方法
 * @author Administrator
 *
 */

@PreMatching
@Provider
@Priority(1)
public class JserseyAuthFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("JserseyAuthFilter ...");
		requestContext.setSecurityContext(new SecurityContext() {
			
			@Override
			public boolean isUserInRole(String role) {
				return "sunqi".equals(role);
			}
			
			@Override
			public boolean isSecure() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Principal getUserPrincipal() {
				return new Principal() {
					
					@Override
					public String getName() {
						return "sunqi";
					}
				};
			}
			
			@Override
			public String getAuthenticationScheme() {
				return null;
			}
		});
	}

}
