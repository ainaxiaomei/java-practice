package com.practice.java.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * lock和unlock必须成对出现。
 * lock对state+1,unlock对state-1
 * 只有state为0时才能获取锁
 * @author Administrator
 *
 */
public class RenentrantLockTest {

	@Test
	public void test() throws InterruptedException {
		
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
//		lock.lock();
//		lock.lock();
//		lock.lock();
		lock.unlock();
		new Thread(()->{
			System.out.println("准备获得锁 。。。");
			lock.lock();
			System.out.println("在另一个线程获得锁！");
		}).start();
		
		TimeUnit.SECONDS.sleep(10);
	}

}
