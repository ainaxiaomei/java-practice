package com.practice.java.concurrent.unsafe;

import java.lang.reflect.InvocationTargetException;

public class CallerSensitiveClassTest {
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		System.setProperty("java.ext.dirs", "F:/app;$JAVA_HOME/jre/lib/ext");
//		ClassLoader extClassLoader = Thread.currentThread().getContextClassLoader().getParent();
//		Class c = extClassLoader.loadClass("com.practice.java.concurrent.unsafe.CallerSensitiveClass");
//		CallerSensitiveClass test  = (CallerSensitiveClass) c.getConstructor().newInstance();
//		test.callerSensitiveMethod();
		
		System.out.println(CallerSensitiveClassTest.class.getClassLoader());
		CallerSensitiveClass test = new CallerSensitiveClass();
		test.callerSensitiveMethod();
	}
	

}
