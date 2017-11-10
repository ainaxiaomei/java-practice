package com.practice.tomcat;

import java.util.concurrent.TimeUnit;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;


/**
 * jersey支持的异步资源，使用前不仅设置ServletContainer为异步的。同时需要
 * servler3.0以上版本
 * 
 * 使用jersey的权限控制,权限控制是在找到匹配方法后才执行，即是在pre-match过滤器之后
 * "/home" 任何人都能方法
 * "/protect" 只有sunqi才能访问
 * @author Administrator
 *
 */
@Path(value = "/async")
@PermitAll
public class JerseyAsyncResource {
	
	@Context
	private SecurityContext securityContext;
	
	@Path("/home")
	@GET
	@Async
	public void async(@Suspended AsyncResponse response){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
					String name = securityContext.getUserPrincipal().getName();
					response.resume("Async Jersey : path /home, role " + name);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
		
	}
	
	@RolesAllowed("sunqi")
	@Path("/protect")
	@GET
	@Async
	public void protect(@Suspended AsyncResponse response){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
					String name = securityContext.getUserPrincipal().getName();
					response.resume("Async Jersey : path /protect, role " + name);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
		
	}
}
