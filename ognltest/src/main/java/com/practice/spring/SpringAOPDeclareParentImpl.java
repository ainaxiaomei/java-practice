package com.practice.spring;

public class SpringAOPDeclareParentImpl implements SpringAOPDeclareParent {

	@Override
	public String print() {
		System.out.println("SpringAOPDeclareParent!");
		return "SpringAOPDeclareParent";
	}

}