package com.practice.java;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandlerTest {
	public void print(String str){
		System.out.println(str);
	}
	public static void main(String[] args) throws Throwable {
//		MethodType mt1 = MethodType.methodType(String.class);
//		MethodHandle mh1 = MethodHandles.lookup().findVirtual(MethodHandlerTest.class, "toString",mt1);
//		String str = (String)mh1.invoke(new MethodHandlerTest());
//		System.out.println(str);
		
		
		MethodType mt2 = MethodType.methodType(void.class, String.class);
		MethodHandle mh2 = MethodHandles.lookup().findSpecial(MethodHandlerTest.class, "print", mt2, MethodHandlerTest.class);
		mh2.invoke(new MethodHandlerTest(),"123");
	}
}
