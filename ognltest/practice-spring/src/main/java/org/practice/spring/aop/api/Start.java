package org.practice.spring.aop.api;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.practice.spring.validate.Foo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.BeanFactoryUtils;

public class Start {

	public static void main(String[] args) {
		
		ProxyFactory factory = new ProxyFactory();
		FooImpl foo = new FooImpl();
		factory.setTarget(foo);
		factory.setInterfaces(IFoo.class);
		factory.setProxyTargetClass(false);
		factory.setExposeProxy(true);
		factory.addAdvice(new MethodInterceptor() {
			
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				
				System.out.println("--- MethodInterceptor Before ");
				Object res = invocation.proceed();
				System.out.println("--- MethodInterceptor After ");
				return res;
			}
		});
		factory.addAdvice(new MethodBeforeAdvice() {

			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				
				System.out.println("--- BeforeAdvice");
				
			}
			
			
		});
		IFoo fo = (IFoo) factory.getProxy();
		System.out.println(fo.getClass());
		fo.foo();
		

	}

}
