package org.practice.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class FooImpl implements IFoo {

	@Override
	public String foo(String name,int age) {
		
		System.out.println("--- do method ");
		return "i am " + name + " age is " + age;
	}

}
