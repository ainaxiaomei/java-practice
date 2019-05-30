package com.practice.redis.secondaryindex;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisStoreTest {
	
	public static void main(String[] args) {
		
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("192.168.2.3");
		factory.setPort(6379);
		factory.afterPropertiesSet();
		
		RedisTemplate<String, String> template =  new RedisTemplate();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new StringRedisSerializer());
		
		template.setConnectionFactory(factory);
		template.afterPropertiesSet();
		
		 HashOperations<String, String, String> hash = template.opsForHash();
		 
		
		
		
	}
	
	static class Persson {
		
		private String name ;
		
		private String gender;
		
		private String age;
		
		private String score;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String getScore() {
			return score;
		}

		public void setScore(String score) {
			this.score = score;
		}
		
		
	}

}
