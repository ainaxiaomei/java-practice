package com.practice.java;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedualExecutorService {
	public static void main(String[] args) {
		ScheduledExecutorService exe = Executors.newScheduledThreadPool(4);
		System.out.println("start ...");
//		exe.schedule(() -> {
//			System.out.println("execute !");
//		}, 10, TimeUnit.SECONDS);

		exe.scheduleWithFixedDelay(() -> {
			try {
				System.out.println(System.currentTimeMillis() + " task start " + Thread.currentThread().getName());
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, 0, 1, TimeUnit.SECONDS);
		
//		exe.scheduleAtFixedRate(()->{
//			try {
//				System.out.println(TimeUnit.SECONDS.toSeconds(System.currentTimeMillis())+" task start " + Thread.currentThread().getName());
//				Thread.sleep(5000);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}, 0, 1, TimeUnit.SECONDS);
//		

		//exe.shutdown();
	}
}
