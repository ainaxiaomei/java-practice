package com.practice.netflix.hystrix;

import java.util.concurrent.ExecutionException;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommand.Setter;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public class HystrixTest {
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		System.out.println(" --- 主线程 " + Thread.currentThread());
		
		
		//获取域名列表命令
		Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("L3"))
		.andCommandKey(HystrixCommandKey.Factory.asKey("domainList"))
		.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("L3-list-threadPool"))
		.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10));
		
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		
		HystrixCommand<String> domainCommand = new HystrixCommand<String>(setter) {

			@Override
			protected String run() throws Exception {
				
				System.out.println(" --- domain command thread " + Thread.currentThread());
				return "baidu.com";
			}

			@Override
			protected String getFallback() {
				return "获取域名失败！";
			}

			@Override
			protected String getCacheKey() {
				return "sunqi.com";
			}
			
			
			
			
			
		};
		
		String str = domainCommand.execute();
		
		System.out.println(domainCommand.getClass().getSimpleName() + " return " + str);
		
		
		setter.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("L3-refresh-threadPool"));
		HystrixCommand<String> refreshCommand = new HystrixCommand<String>(setter){

			@Override
			protected String run() throws Exception {
				System.out.println(" --- refresh command thread " + Thread.currentThread());
				return "refresh success !";
			}
			
		};
		
		str = refreshCommand.execute();
		System.out.println(refreshCommand.getClass().getSimpleName() + " return " + str);
		
		
		
	}
	


}


