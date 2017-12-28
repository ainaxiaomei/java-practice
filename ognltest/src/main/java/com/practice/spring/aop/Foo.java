package com.practice.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Foo implements IFoo {
	
	private String name;
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String doBusiness(int arg) {
		System.out.println("do business in " + getClass());
		return "Foo";
	}

}
