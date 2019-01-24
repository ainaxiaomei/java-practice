package com.practice.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	
	
	public static void main(String[] args) {
		
//		ExecutorService service = Executors.newCachedThreadPool();
//		service.submit(()->{
//			while(true) {
//				
//			}
//		});
		
		new Thread(()->{
			while(true) {
				
			}
		}) {}.start();
		System.out.println("exit");
	}
}
