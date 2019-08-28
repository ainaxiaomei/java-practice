package org.practice.spring.aop.api;

import org.springframework.aop.framework.AopContext;

public class FooImpl implements IFoo {

	@Override
	public void foo() {
		System.out.println(" -- invoke foo ");
		//bar();
		((IFoo) AopContext.currentProxy()).bar();

	}

	@Override
	public void bar() {
        System.out.println(" -- invoke bar ");
	}

}
