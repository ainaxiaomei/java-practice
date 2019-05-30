package com.practice.tomcat.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class WebConfiguration {
	@Bean
    public JedisConnectionFactory  connectionFactory() {
		JedisConnectionFactory  factory = new JedisConnectionFactory ();
		factory.setHostName("192.168.2.3");
		return factory;
    }
	
	
}
