package com.practice.redis.lock;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * 
    * @ClassName: RedisLockTest  
    * @Description: 使用redis实现一个分布式锁
    * 
    *  1.先使用setnx 设置当前时间 + 超时时间 + 1 ，如果设置成功获取锁
    *  2.如果失败，使用get方法获取锁的时间，如果没有超时，自旋一段时间
    *  3.如果已经超时，使用getset设置当前时间 + 超时时间 + 1 ,获取返回值并检查是否超时,如果超时获取成功
    *  4.如果未超时重复第一步
    * @author win  
    * @date 2019年2月13日  
    *
 */
public class RedisLockTest {
	
	
	public void redisLock(RedisTemplate<String, Long> template) throws InterruptedException {
		
		 while (true) {
			 
			 long value = System.currentTimeMillis() + 10000 + 1 ;
				boolean a = template.opsForValue().setIfAbsent("sunqi", value);
				
				if (a) {
					
					return ;
				} else {
					
					long v = template.opsForValue().get("sunqi");
					
					long currentValue  = System.currentTimeMillis() + 10000 + 1 ;
					
					if (isExpired(v,currentValue)) {
						//已经超时
						currentValue  = System.currentTimeMillis() + 10000 + 1 ;
						v = template.opsForValue().getAndSet("sunqi", currentValue);
						
						if (isExpired(v,currentValue)) {
							//已经超时,获取成功
							return ;
						} else {
							
						}
						
					} else {
						
						//自旋一段时间
						TimeUnit.MILLISECONDS.sleep(20);
					}
					
					
				}
			 
		 }
		
		
		 
		
		
		
	}
	
	public boolean isExpired(long value ,long current) {
		
		if (current > value) {
			
			return true ;
		} else {
			
			return false;
		}
		
	}
	
	
	public static void main(String[] args) {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("192.168.2.3");
		factory.setPort(6379);
		factory.afterPropertiesSet();
		
		RedisTemplate<String, Long> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		
		
		
		
	}

}
