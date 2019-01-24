package com.practice.java.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	
	public static void main(String[] args) {
		
		Semaphore sem = new Semaphore(2);
		
		try {
			
			int avaliablePermits = sem.availablePermits();
			
			System.out.println(" --- avaliablePermits : " + avaliablePermits);
			
			sem.acquire();
			
			avaliablePermits = sem.availablePermits();
			System.out.println(" --- avaliablePermits : " + avaliablePermits);
			
			if (sem.tryAcquire()) {
				System.out.println(" --- get permits success !");
			} else {
				
				System.out.println(" --- get permits fail !");
			}
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
