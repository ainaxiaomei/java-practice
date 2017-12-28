package com.practice.java.desginpattern.iterator;

import java.util.List;

public class MethodInvocation {

	private List<MethodInteceptor> list;
	
	public MethodInvocation(List<MethodInteceptor> list) {
		super();
		this.list = list;
	}

	private int current;
	
	public void procced(){
		if(current == list.size()){
			
			System.out.println("doing business ...");
			return ;
		}
		
		MethodInteceptor inteceptor = list.get(current);
		current ++;
		inteceptor.Intecept(this);
		
		
	}
}
