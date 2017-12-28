package com.practice.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class Config {
	
	@Bean(name="unproxy")
	public IBar upproxyBean(){
		return new Bar();
	}
}
