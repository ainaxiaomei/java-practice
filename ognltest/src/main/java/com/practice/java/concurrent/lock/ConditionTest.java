package com.practice.java.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
	
	private ReentrantLock lock = new ReentrantLock();
	
	public Condition conditon = lock.newCondition();
	
	public void Test(){
		try {
			lock.lock();
			
			System.out.println(Thread.currentThread());
			
			conditon.await();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ConditionTest t =new ConditionTest();
		new Thread(()->{
			t.Test();
		}).start();
		
		new Thread(()->{
			t.Test();
		}).start();
		
	}
}
