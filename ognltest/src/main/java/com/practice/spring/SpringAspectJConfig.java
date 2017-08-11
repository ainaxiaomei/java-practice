package com.practice.spring;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configurable
@EnableAspectJAutoProxy
public class SpringAspectJConfig {
	
	@Bean
	public SpringAspectJDO newSpringAspectJDO(){
		SpringAspectJDO obj = new SpringAspectJDO();
		obj.setTag("AOP");
		return obj;
	}
}
