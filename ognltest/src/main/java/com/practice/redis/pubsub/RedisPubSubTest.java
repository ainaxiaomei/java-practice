package com.practice.redis.pubsub;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisPubSubTest {
	
	
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
		
		
//		ListOperations<String, String> listOps = template.opsForList();
//		listOps.leftPush("sunqi", "2");
//		List<String> ls = listOps.range("sunqi", 0, 10);
//		
//		System.out.println(ls);
		
		
		/**
		 * 两种publish方式
		 * 1.通过redisTemplate的convertAndSend
		 * 2.通过redisConnection的publish方法
		 */
		template.convertAndSend("sunqi", "hello1");
		template.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.publish("sunqi".getBytes(), "word1".getBytes());
				return null;
			}
		});
		
		
		RedisMessageListenerContainer listenerContainer = new RedisMessageListenerContainer();
		
		
		MessageListener listener = new MessageListener() {
			
			@Override
			public void onMessage(Message message, byte[] pattern) {
				System.out.println(" --- async listener receive message : " + message);
			}
		};
		
		Map<MessageListener, Collection<? extends Topic>> listenners = new HashMap<>();
		listenners.put(listener, Arrays.asList(new ChannelTopic("sunqi")));
		listenerContainer.setMessageListeners(listenners);
		
		
		listenerContainer.setConnectionFactory(factory);
		listenerContainer.setSubscriptionExecutor(Executors.newFixedThreadPool(1));
		listenerContainer.setTaskExecutor(Executors.newFixedThreadPool(1));
		listenerContainer.start();
		
		/**
		 * 同步的订阅方式，会阻塞进程
		 */
		template.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.subscribe(new MessageListener() {
					
					@Override
					public void onMessage(Message message, byte[] pattern) {
						System.out.println(" --- sync listener receive message : " + message);
						
					}
				},"sunqi".getBytes());
				return null;
			}
		});
		
		System.out.println(" --- end ");
	}

}
