package org.practice.spring.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AopConfig {
	
	@Bean
	@Lazy
	public IFoo createFoo() {
		return new Foo();
	}

}
