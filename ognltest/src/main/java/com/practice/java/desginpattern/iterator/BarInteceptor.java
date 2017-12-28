package com.practice.java.desginpattern.iterator;

public class BarInteceptor implements MethodInteceptor {

	@Override
	public void Intecept(MethodInvocation invocation) {
		System.out.println("bar before invoke ...");
		invocation.procced();
		System.out.println("bar after invoke ...");
	}

}
