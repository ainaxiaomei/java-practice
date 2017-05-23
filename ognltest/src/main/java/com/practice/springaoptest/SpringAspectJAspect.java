package com.practice.springaoptest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SpringAspectJAspect {
	
	@DeclareParents(value="snail.ognltest.SpringAspectJDO",defaultImpl=SpringAOPDeclareParentImpl.class)
	public SpringAOPDeclareParent inter;
	
	@Pointcut("execution(* snail.ognltest.*.printTag(..))")
	public void bPointCut(){
		
	}
	
	@Before(value="bPointCut() && args(arg)")
	public void beforeAdvice(JoinPoint jopint,String arg){
		System.out.println("[--");
		System.out.println("\trequest parameter is :" + arg);
		System.out.println("\tbefore invoke ...");
		System.out.println("--]");
	}
	
	@AfterReturning(pointcut="bPointCut()",returning="retVal")
	public void afterReturningAdvice(String retVal){
		System.out.println("[--");
		System.out.println("\tafter returning ...");
		System.out.println("\t"+ String.format("return value is %s", retVal));
		System.out.println("--]");
	}
	
	@After(value="bPointCut()")
	public void afterAdvice(){
		System.out.println("[--");
		System.out.println("\tafter finally ...");
		System.out.println("--]");
	}
	
	@Around(value="bPointCut()")
	public Object aroundAdvice(ProceedingJoinPoint jointPoint) throws Throwable{
		System.out.println("[--");
		System.out.println("\taround advice before ...");
		Object str = jointPoint.proceed(new Object[]{"Around"});
		System.out.println("\taround advice after ...");
		System.out.println("--]");
		return "123";
		
	}
	
	
}
