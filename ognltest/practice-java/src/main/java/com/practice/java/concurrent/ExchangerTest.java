package com.practice.java.concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {
	
	public static void main(String[] args) {
		
		Exchanger<String> ex = new Exchanger<String>();
		
       new Thread(()->  {
			
			
			try {
				TimeUnit.SECONDS.sleep(2);
				String res = ex.exchange(Thread.currentThread().getName());
				System.out.println(" --- " + Thread.currentThread().getName() + "exchanged");
				System.out.println(" --- " + Thread.currentThread().getName() + "get " + res);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}) .start();
		
       new Thread(()->  {
			
			
			try {
				String res = ex.exchange(Thread.currentThread().getName());
				System.out.println(" --- " + Thread.currentThread().getName() + "exchanged");
				System.out.println(" --- " + Thread.currentThread().getName() + "get " + res);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}) .start();
		
	}

}
