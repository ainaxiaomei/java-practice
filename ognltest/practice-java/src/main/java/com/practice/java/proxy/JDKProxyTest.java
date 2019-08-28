package com.practice.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyTest {
	
	interface Foo {
		
		void foo();
	}
	
	static class FooImpl implements Foo{

		@Override
		public void foo() {
			
			System.out.println("--- fooimpl");
			
		}
		
	}
	
	static class MyHandler implements InvocationHandler{
		
		private Foo target;

		public MyHandler(Foo target) {
			super();
			this.target = target;
		}



		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			
			System.out.println("--- before");
			
			return method.invoke(target);
			 
		}
		
	}
	

	public static void main(String[] args) {
		
		Foo foo = (Foo) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), 
				new Class[] {Foo.class},new MyHandler(new FooImpl()) );
		
		foo.foo();

	}

}
