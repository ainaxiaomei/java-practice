package com.practice.java.concurrent.unsafe;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import sun.misc.Unsafe;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

public class CallerSensitiveClassTest2 {
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		B b = new B();
		b.b();
		
		
	}

	
	public static class A {
		
		public void a (CallerSensitiveClass c) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			CallerSensitiveClass.class.getMethod("callerSensitiveMethod").invoke(c);
		
			
		}
		
	}
	
	public static class  B{
		
		public void b () throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			
			A a = new A();
			A.class.getMethod("a",CallerSensitiveClass.class).invoke(a, new CallerSensitiveClass());
			
		}
		
	}
	
	
	

}
