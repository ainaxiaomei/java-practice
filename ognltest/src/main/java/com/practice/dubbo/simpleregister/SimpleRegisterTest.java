package com.practice.dubbo.simpleregister;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleRegisterTest {
	
	@Before
	public void init() {
		ClassPathXmlApplicationContext registerCtx = new ClassPathXmlApplicationContext("com/practice/dubbo/simpleregister/register-app.xml");
		registerCtx.start();
	}

	@Test
	public void test() {
		
	}

}
