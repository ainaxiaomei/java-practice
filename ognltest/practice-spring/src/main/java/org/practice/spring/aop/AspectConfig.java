package org.practice.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class AspectConfig {
	
	//使用JoinPoint来获取参数
	/**
	@Before(value="execution(* org.practice.spring.aop.*.*(..))")
	public void beforeAdvice(JoinPoint param) {
		System.out.println(" --- before invoke , param is " + Arrays.toString(param.getArgs()));
	};
	*/
	
	@Before(value="execution(* org.practice.spring.aop.*.*(..)) && args(a1,b1)",argNames="a1,b1")
	public void beforeAdvice0(String a1,int b1) {
		System.out.println(" --- before invoke , param is " + a1 + " " + b1);
	};
	
//	@Before(value="execution(* org.practice.spring.aop.*.*())")
//	public void beforeAdvice1(String param) {
//		System.out.println(" --- before invoke , param is " + param);
//	};
	
	@After(value="execution(* org.practice.spring.aop.*.*(..))")
	public void afterAdvice() {
		
		System.out.println(" --- after invoke");
	};
	
	@AfterThrowing(value="execution(* org.practice.spring.aop.*.*(..))",throwing="err")
	public void afterReturnAdvice(Throwable err) {
		System.out.println(" --- after  throwing invoke");
	};
	
	@AfterReturning(value="execution(* org.practice.spring.aop.*.*(..))",returning="ret")
	public void afterThrowAdvice(String ret) {
		System.out.println(" --- after  returning invoke ," + " retrun value is " + ret);
	};
	
	@Around(value="execution(* org.practice.spring.aop.*.*(..))")
	public void aroundAdvice(ProceedingJoinPoint joinpoint) throws Throwable {
		
		System.out.println("--- before aroundAdvice");
		joinpoint.proceed();
		System.out.println("--- after aroundAdvice");
	};

}
