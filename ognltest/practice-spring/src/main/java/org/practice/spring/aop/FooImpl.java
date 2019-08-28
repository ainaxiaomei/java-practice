package org.practice.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class FooImpl implements IFoo {

	@Override
	public String foo(String foo) {
		return "i am " + foo;
	}

}
