package com.practice.spring.ng;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {
	
	
	/**
	 * 前置通知在方法执行前执行，有两种方式获取方法的参数
	 * 1.通过注入joinpoint对象
	 * 2.通过表达式的arg
	 */
	@Before(value = "execution(public * com.practice.spring.ng.Foo.doBusiness(..)) && args(arg,..)")
	public void beforeDoBusiness(JoinPoint joinPoint,int arg){
		System.out.println(joinPoint);
		System.out.println("before execute " + joinPoint.getSignature().getName() + "\n"
				+ "paraterm is : " + Arrays.toString(joinPoint.getArgs()));
		System.out.println("before execute " + joinPoint.getSignature().getName() + "\n"
				+ "paraterm is : " + arg);
	}

}
