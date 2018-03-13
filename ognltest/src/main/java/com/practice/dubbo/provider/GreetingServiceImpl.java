package com.practice.dubbo.provider;

public class GreetingServiceImpl implements GreetingService {

	@Override
	public String greet() {
		return "Hello Dubbo !";
	}

}
