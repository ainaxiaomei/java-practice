package com.practice.java.concurrent.lock;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import org.junit.Test;


public class LockTest {
    
	@Test
	public void RenentrantTest() throws InterruptedException {
		
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
	
	
	/**
	 * 可以从写锁里获取读锁，从读锁里获取写锁会死锁
	 * 如果一个线程已经持有写锁，不管当前是否有读锁在等待再次获取都能再次读锁
	 * @throws InterruptedException
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void ReadWriteLockTest() throws InterruptedException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		final ReadLock rl = lock.readLock();
		final WriteLock wl = lock.writeLock();
		
		rl.lock();
		System.out.println("获取读锁。。。");
		//从读锁里获取写锁会死锁
//		wl.lock();
//		wl.unlock();
		rl.unlock();
		//从写锁里可以获得读锁
		wl.lock();
		System.out.println("获取写锁。。。");
		rl.lock();
		System.out.println("获取读锁。。。");
		rl.unlock();
		wl.unlock();
		
		//从读锁里获取读锁同时已经有写锁在等待,读锁的优先级高于写锁
		System.out.println("------------------");
		rl.lock();
		new Thread(()->{
			System.out.println("准备获取写锁。。。");
			wl.lock();
			System.out.println("成功获取写锁。。。");
			wl.unlock();
		}).start();
		System.out.println("准备再次获取读锁 。。。");
		TimeUnit.SECONDS.sleep(5);
		rl.lock();
		System.out.println("再次成功获取读锁 。。。");
		rl.unlock();
		rl.unlock();
		TimeUnit.SECONDS.sleep(5);
		
		
		
	}
	
	/**
	 * 
	 * 如果一个读锁想要进入临界区此时已经有排队的写锁读锁会进入队列
	 * 和写锁是同一个队列。
	 * 是读锁还是写锁由node的netwaiter标志
	 * @throws InterruptedException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void ReadWriteLockTest2() throws InterruptedException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		final ReadLock rl = lock.readLock();
		final WriteLock wl = lock.writeLock();
		System.out.println("------------------");
		//获取一个读锁该锁已经有写锁在等待
		wl.lock();
		new Thread(()->{
			System.out.println("准备获取写锁。。。");
			wl.lock();
			System.out.println("成功获取写锁。。。");
			wl.unlock();
		},"read-2").start();
		
		TimeUnit.SECONDS.sleep(5);
		new Thread(()->{
			System.out.println("准备获取读锁。。。");
			rl.lock();
			System.out.println("成功获取读锁。。。");
			rl.unlock();
		},"write-1").start();
		
		Field sync = lock.getClass().getDeclaredField("sync");
		sync.setAccessible(true);
		AbstractQueuedSynchronizer aqs = (AbstractQueuedSynchronizer) sync.get(lock);
		System.out.println("排队的线程 : " + aqs.getQueuedThreads());
		System.out.println("排队的读线程 : " + aqs.getSharedQueuedThreads());
		System.out.println("排队的写线程 : " + aqs.getExclusiveQueuedThreads());
		TimeUnit.SECONDS.sleep(60);
		wl.unlock();
	}

}
