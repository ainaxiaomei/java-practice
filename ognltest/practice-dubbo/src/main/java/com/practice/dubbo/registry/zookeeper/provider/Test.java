package com.practice.dubbo.registry.zookeeper.provider;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	public static void main(String[] args) throws IOException {
		
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("com/practice/dubbo/registry/zookeeper/provider/provider-app.xml");
		app.start();
		System.in.read();
	}

}
