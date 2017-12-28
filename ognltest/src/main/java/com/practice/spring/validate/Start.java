package com.practice.spring.validate;

import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class Start {

	@Test
	public void test() {
		Foo foo = new Foo();
		FooValidator validator = new FooValidator();
		Errors errors = new BeanPropertyBindingResult(foo, "foo");
		validator.validate(foo, errors);
	}
	
	
	/**
	 * BeanWrapper Test 把一个类包装成Bean Wrapper测试
	 * 1.BeanWrapper操作的类会改变原始对象
	 * 2.BeanWrapper获取的对象和原始对象是同一个对象
	 * 3.使用索引设置列表时,不会自动初始化列表
	 */
	@Test
	public void beanWrapperTest() {
		Foo foo = new Foo();
		BeanWrapper wrapper = new BeanWrapperImpl(foo);
		wrapper.setPropertyValue("name", "foo");
		wrapper.setPropertyValue("age", 20);
		wrapper.setPropertyValue("friend[10]","Bar");
		
		//原始对象被改变
		System.out.println(foo);
		
		//true
		System.out.println(foo == wrapper.getWrappedInstance());
	}

}
