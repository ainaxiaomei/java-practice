package org.practice.spring.aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class Start {
	
	private AnnotationConfigApplicationContext context;
	
	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext("com.practice.spring.aop");
	}

	
	/**
	 * 如果没有符合的advise,创建的bean是一个纯对象没有被代理
	 */
	@Test
	public void test() {
		IFoo foo = context.getBean(IFoo.class);
		foo.foo();
		System.out.println(foo.getClass());
		
		GenericApplicationContext con = new GenericApplicationContext();
	}

}
