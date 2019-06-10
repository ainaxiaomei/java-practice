package org.practice.dubbo.springboot;

import org.apache.dubbo.config.annotation.Service;

@Service
public class GreetingServiceImpl implements GreetingService{

	@Override
	public String greet(String str) {
		return "hello " + str;
	}

}
