package com.practice.dubbo.zookeeper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.practice.dubbo.zookeeper.provider.IGreetingService;


public class ZookeeprRegisterTest {
	
	@Before
	public void init() throws InterruptedException {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("com/practice/dubbo/zookeeper/provider/provider-app.xml");
		app.start();
	}
	
	@Test
	public void consumerTest() throws InterruptedException {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("com/practice/dubbo/zookeeper/consumer/consumner-app.xml");
		app.start();
		
		IGreetingService service = (IGreetingService)app.getBean("greetingService");
		System.out.println(service.greet());
	}

}
