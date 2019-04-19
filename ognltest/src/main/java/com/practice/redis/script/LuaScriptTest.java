package com.practice.redis.script;

import java.util.Arrays;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class LuaScriptTest {
	
	public static void main(String[] args) {
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("192.168.2.3");
		factory.setPort(6379);
		factory.afterPropertiesSet();
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		
	    template.execute(new DefaultRedisScript<Long>("redis.call('set',KEYS[1],ARGV[1])",Long.class),
	    		Arrays.asList("sunqi"),"123");
		
	}

}
