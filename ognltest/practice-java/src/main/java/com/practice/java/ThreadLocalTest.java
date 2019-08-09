package com.practice.java;

import java.lang.reflect.Field;

import org.junit.Test;



public class ThreadLocalTest {
	
	public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    
	/**
	 * 同一个ThreadLocal在不同的线程中的key是不是相同
	 * @author win
	 *
	 */
	@Test
	public void keyTest() throws InterruptedException {
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
	
	
	/**
	 * 同一个Threadlocal在同一个线程里只能有一个值
	 */
	@Test
	public void valueTest() {
		
		threadLocal.set("a");
		threadLocal.set("b");
		
		System.out.println(threadLocal.get());
		
		
	}
	

}
