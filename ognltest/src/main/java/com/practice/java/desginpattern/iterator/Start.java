package com.practice.java.desginpattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class Start {
	
	public static void main(String[] args) {
		List<MethodInteceptor> list = new ArrayList<>();
		list.add(new FooInteceptor());
		list.add(new BarInteceptor());
		MethodInvocation invocation = new MethodInvocation(list);
		invocation.procced();
		
	}
	
}
