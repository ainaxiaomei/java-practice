package org.practice.spring.aop;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Start {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.practice.spring.aop");
		context.getBean(IFoo.class).foo("sunqi",120);
		String[] beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context.getBeanFactory(), MethodInterceptor.class);
		System.out.println(Arrays.toString(beanNames));
		
		
	}

}
