package org.practice.spring.aop.api;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class Start {

	public static void main(String[] args) {
		
		ProxyFactory factory = new ProxyFactory();
		FooImpl foo = new FooImpl();
		factory.setTarget(foo);
		factory.setExposeProxy(true);
		factory.addAdvice(new MethodBeforeAdvice() {

			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				
				System.out.println("--- before");
				
			}
			
			
		});
		IFoo fo = (IFoo) factory.getProxy();
		fo.foo();

	}

}
