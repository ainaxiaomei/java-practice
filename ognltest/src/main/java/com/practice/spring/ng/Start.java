package com.practice.spring.ng;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Start {
	
	private AnnotationConfigApplicationContext context;
	
    @Before
    public void init(){
		context = new AnnotationConfigApplicationContext("com.practice.spring.ng");

    }
    
    /**
     * 没有配置任何advice pointcut的bean是一个纯洁的bean
     */
	@Test
	public void test() {
		IBar bar = context.getBean("unproxy",IBar.class);
		System.out.println(bar.getClass());
		assertTrue("ERROR", bar.getClass() == Bar.class);
	}
	
	
	@Test
	public void test2() {
		IFoo foo = context.getBean(IFoo.class);
		foo.doBusiness(1000);
	}
	
	

}
