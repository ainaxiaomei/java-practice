package com.practice.jmx;

public class Hello implements HelloMBean {
	
	private String name;

	@Override
	public String getName() {
		System.out.println("invoke getName ...");
		return name;
	}

	@Override
	public String setName(String name) {
		System.out.println("invoke setName ...");
		return this.name = name;
	}


}
