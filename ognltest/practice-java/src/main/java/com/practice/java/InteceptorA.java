package com.practice.java;

public class InteceptorA implements Inteceptor{

	public String doIntecpt(Controller c) {
		
		System.out.println("before InteceptorA...");
		System.out.println("do InteceptorA... ");
		c.process();
		System.out.println("after InteceptorA...");
		return null;
	}

}
