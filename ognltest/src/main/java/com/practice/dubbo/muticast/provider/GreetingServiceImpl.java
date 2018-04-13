package com.practice.dubbo.muticast.provider;

public class GreetingServiceImpl implements GreetingService {

	@Override
	public String greet() {
		return "Hello Dubbo !";
	}

}
