package com.practice.java;

import java.lang.reflect.Field;

import org.junit.Test;


/**
 * 同一个ThreadLocal在不同的线程中的key是不是相同
 * @author win
 *
 */
public class ThreadLocalTest {
	
	public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

	@Test
	public void test() throws InterruptedException {
		new Thread(()->{
			
			System.out.println("-- " + Thread.currentThread().getName() );
			try {
				Field f = threadLocal.getClass().getDeclaredField("threadLocalHashCode");
				f.setAccessible(true);
				int code =  f.getInt(threadLocal);
				System.out.println("threadLocalHashCode : " + code);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			
		}).start();
		
		new Thread(()->{
			
			System.out.println("-- " + Thread.currentThread().getName() );
			try {
				Field f = threadLocal.getClass().getDeclaredField("threadLocalHashCode");
				f.setAccessible(true);
				int code =  f.getInt(threadLocal);
				System.out.println("threadLocalHashCode : " + code);
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}).start();
		
		Thread.sleep(5000);
	}

}
