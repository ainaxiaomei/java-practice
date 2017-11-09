package com.practice.tomcat;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;


/**
 * jersey支持的异步资源，使用钱不许设置ServletContainer为异步的。同时需要
 * servler3.0以上版本
 * @author Administrator
 *
 */
@Path(value = "/async")
public class JerseyAsyncResource {
	
	@Path("/echo")
	@GET
	@Async
	public void async(@Suspended AsyncResponse response){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(2);
					response.resume("Hello Async Jersey !");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
		
	}
}
