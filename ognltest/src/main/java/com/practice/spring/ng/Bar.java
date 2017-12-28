package com.practice.spring.ng;

public class Bar implements IBar {
	
	private String name;
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String doBusiness() {
		System.out.println("do business in " + getClass());
		return "Foo";
	}

}
