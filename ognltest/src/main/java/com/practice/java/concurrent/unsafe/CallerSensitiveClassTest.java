package com.practice.java.concurrent.unsafe;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import sun.misc.Unsafe;
import sun.reflect.Reflection;

public class CallerSensitiveClassTest implements Caller{
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		System.setProperty("java.ext.dirs", "E:/workspace/java-practice/ognltest/target/classes;$JAVA_HOME/jre/lib/ext");
//		ClassLoader extClassLoader = Thread.currentThread().getContextClassLoader().getParent();
//		Class c = extClassLoader.loadClass("com.practice.java.concurrent.unsafe.CallerSensitiveClass");
//		CallerSensitiveClass test  = (CallerSensitiveClass) c.getConstructor().newInstance();
//		test.callerSensitiveMethod();
		
		
		//通过正常对象调用Reflection.getCallerClass()
//		System.out.println(CallerSensitiveClassTest.class.getClassLoader());
//		CallerSensitiveClass test = new CallerSensitiveClass();
//		//test.callerSensitiveMethod();
//		
//		//通过双重反射方式调用
//		
//		Caller c =(Caller) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
//				new Class[] {Caller.class}, new MyInvocationHandler());
//		
//		c.call();
		
		
		
		
	}

	@Override
	public void call() {
		
	}
	
	public static class MyInvocationHandler implements InvocationHandler {
		
		private CallerSensitiveClassTest obj = new CallerSensitiveClassTest();
		
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			
			CallerSensitiveClass test = new CallerSensitiveClass();
			test.callerSensitiveMethod();
			return method.invoke(obj, args);
		}
	}
	
	
	
	
	

}
