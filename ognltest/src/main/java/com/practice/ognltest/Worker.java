package com.practice.ognltest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Worker {
	public static void main(String[] args) {
		ExecutorService ex = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, 
				TimeUnit.SECONDS, new SynchronousQueue<>());
		
//		ex.submit(()->{
//			throw new RuntimeException("error");
//		});
		
	
		ex.execute(()->{
			Thread t = Thread.currentThread();
			while(true){
				if(t.isInterrupted()){
					System.out.println("Interrupted ...");
				}
				
				
			}
		});
		ex.shutdownNow();
		System.out.println("finish");
		
		
	}

}
