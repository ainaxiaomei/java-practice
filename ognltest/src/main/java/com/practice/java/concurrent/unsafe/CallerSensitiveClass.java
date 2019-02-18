package com.practice.java.concurrent.unsafe;

import sun.reflect.Reflection;

public class CallerSensitiveClass {
	
	@sun.reflect.CallerSensitive
	public void callerSensitiveMethod() {
		
		
		Class CallerClass = Reflection.getCallerClass();
		System.out.println("caller class is ：" + CallerClass);
		System.out.println("caller classloader is  ：" + CallerClass.getClassLoader());
		
	}

}
