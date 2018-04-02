package com.practice.guava;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

	@Test
	public void test() {
		
		//一秒只能处理两个请求
		RateLimiter limiter = RateLimiter.create(2);
		System.out.println("-- rate :" + limiter.getRate());
		
		for(int i=0 ; i< 10000; i++) {
			
			limiter.acquire();
			System.out.println(System.currentTimeMillis() + ":" + i);
			
		}
	}

}
