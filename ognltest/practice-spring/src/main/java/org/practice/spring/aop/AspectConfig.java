package org.practice.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class AspectConfig {
	
	@Before(value="execution(* org.practice.spring.aop.FooImpl.foo())",argNames="param")
	public void beforeAdvice(String param) {
		System.out.println(" --- before invoke , param is " + param);
	};
	
	public void afterAdvice(String param) {};
	
	public void afterReturnAdvice(String param) {};
	
	public void afterThrowAdvice(String param) {};
	
	public void aroundAdvice(String param) {};

}
