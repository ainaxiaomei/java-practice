package org.practice.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Start {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.practice.spring.aop");
		context.getBean(IFoo.class).foo("sunqi",120);
		
		
	}

}
