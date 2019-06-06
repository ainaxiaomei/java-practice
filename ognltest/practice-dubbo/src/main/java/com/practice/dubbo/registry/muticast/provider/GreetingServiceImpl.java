package com.practice.dubbo.registry.muticast.provider;

import java.util.concurrent.TimeUnit;

public class GreetingServiceImpl implements GreetingService {

	@Override
	public String greet() {
		return "Hello Dubbo !";
	}

	@Override
	public String greetWithSleep() {
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello with sleep";
	}

}
