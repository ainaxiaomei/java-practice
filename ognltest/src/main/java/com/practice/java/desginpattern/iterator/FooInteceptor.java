package com.practice.java.desginpattern.iterator;

public class FooInteceptor implements MethodInteceptor {

	@Override
	public void Intecept(MethodInvocation invocation) {
		System.out.println("foo before invoke ...");
		invocation.procced();
		System.out.println("foo after invoke ...");
	}

}
