package com.practice.dubbo.zookeeper.provider;

public class GreetingServiceImpl implements IGreetingService {

	@Override
	public String greet() {
		return "Hello Dubbo !";
	}

}
