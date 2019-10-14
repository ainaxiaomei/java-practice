package com.practice.java.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDoenLatchTest {
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);
		
		new Thread(()-> {
			
			    try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				latch.countDown();
				System.out.println( " --- " + Thread.currentThread().getName() + " countDown");
		}).start();
		
		new Thread(()-> {
			    try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			    latch.countDown();;
			System.out.println( " --- " + Thread.currentThread().getName() + " countDown");
		}).start();
		
		System.out.println("--- wait start");
		latch.await();
		System.out.println("--- wait end");
	}

}
