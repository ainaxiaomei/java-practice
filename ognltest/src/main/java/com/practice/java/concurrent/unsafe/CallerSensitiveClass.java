package com.practice.java.concurrent.unsafe;

import sun.reflect.Reflection;

public class CallerSensitiveClass {
	
	@sun.reflect.CallerSensitive
	public void callerSensitiveMethod() {
		
		System.out.println("--- Reflection.getCallerClass()");
		Class CallerClass = Reflection.getCallerClass();
		System.out.println("caller class is :" + CallerClass);
		System.out.println("caller classloader is  : " + CallerClass.getClassLoader());
		
		System.out.println("--- Reflection.getCallerClass(2)");
		CallerClass = Reflection.getCallerClass(2);
		System.out.println("caller class is :" + CallerClass);
		System.out.println("caller classloader is  : " + CallerClass.getClassLoader());
		
	}

}
