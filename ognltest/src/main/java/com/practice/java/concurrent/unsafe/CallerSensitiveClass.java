package com.practice.java.concurrent.unsafe;

import sun.reflect.Reflection;

public class CallerSensitiveClass {
	
	@sun.reflect.CallerSensitive
	public void callerSensitiveMethod() {
		
		Reflection.getCallerClass();
		System.out.println("111");
		
	}

}
