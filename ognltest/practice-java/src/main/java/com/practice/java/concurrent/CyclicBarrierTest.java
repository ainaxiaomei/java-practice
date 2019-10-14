package com.practice.java.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {
	
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		CyclicBarrier c = new CyclicBarrier(3);
		
		new Thread(()->  {
			
			
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println(" --- " + Thread.currentThread() + "parepared");
				c.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(" --- " + Thread.currentThread() + "start");
		}) .start();
		
        new Thread(()->  {
			
			System.out.println(" --- " + Thread.currentThread() + "parepared");
			try {
				c.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(" --- " + Thread.currentThread() + "start");
		}) .start();
        
        new Thread(()->  {
			
			System.out.println(" --- " + Thread.currentThread() + "parepared");
			try {
				c.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(" --- " + Thread.currentThread() + "start");
		}) .start();
		
	}

}
