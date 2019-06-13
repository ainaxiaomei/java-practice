package org.practice.dubbo.springboot;

import org.apache.dubbo.config.annotation.Service;

@Service
public class GreetingServiceImpl implements IGreetingService {

	@Override
	public String greet() {
		return "hello!";
	}

}
