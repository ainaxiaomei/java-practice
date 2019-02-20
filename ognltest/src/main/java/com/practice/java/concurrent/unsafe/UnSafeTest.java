package com.practice.java.concurrent.unsafe;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import sun.misc.Unsafe;

public class UnSafeTest {
	
	public static void main(String[] args) {
		
		
		Caller c = (Caller) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader().getParent(), new Class[] {Caller.class}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				Unsafe.getUnsafe();
				return null;
			}
		});
		
		System.out.println(c.getClass());
		c.call();
	}

}
