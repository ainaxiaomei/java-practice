package com.practice.java.concurrent.unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import sun.misc.Unsafe;

public class CallerSensitiveClassTest {
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
//		ClassLoader extClassLoader = Thread.currentThread().getContextClassLoader().getParent();
//		Class c = extClassLoader.loadClass("com.practice.java.concurrent.unsafe.CallerSensitive");
//		CallerSensitiveClass test  = (CallerSensitiveClass) c.getConstructor().newInstance();
//		test.callerSensitiveMethod();
		
		 Field f = Unsafe.class.getDeclaredField("theUnsafe");
		 f.setAccessible(true);
		 Unsafe unsafe = (Unsafe) f.get(null);
		
	}
	

}
