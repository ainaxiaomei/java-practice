package com.practice.dubbo.simpleregister.provider;

public class GreetingServiceImpl implements GreetingService {

	@Override
	public String greet() {
		return "Hello Dubbo !";
	}

}
