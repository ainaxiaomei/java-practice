package org.practice.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EnvironmentTest {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(System.getProperty("test"));
		System.out.println(System.getenv("test"));
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		String str = context.getEnvironment().getProperty("test");
		while(true){
			
			Thread.sleep(2000);
			System.out.println(String.format("test property is %s", str));
		}
	}
}
