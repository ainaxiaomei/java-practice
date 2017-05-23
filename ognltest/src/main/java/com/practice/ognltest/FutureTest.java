package com.practice.ognltest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class FutureTest {
	
	public void sleep(long time){
		long deadLine = System.nanoTime() + time;
		
		while(deadLine - System.nanoTime() > 0){
			//System.out.println(111);
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		@SuppressWarnings("unchecked")
//		FutureTask<?> task = new FutureTask<Object>( ()->{
//			System.out.println("running ...");
//			//throw new RuntimeException("error");
//			Thread.currentThread().interrupt();
//			System.out.println(1234);
//		},null);
//		Thread t = new Thread(task);
//		t.start();
//		System.out.println("start get ...");
//		System.out.println(task.get());
		
		
//		Thread t2 = new Thread(()->{
//			while(true){
//				
//			}
//		});
//		
//		t2.start();
//		
//		t2.interrupt();
//		System.out.println("222222"+t2.isInterrupted());
		
//		FutureTask<String> task = new FutureTask<String>(()->{
//			Thread t = Thread.currentThread();
//			while(true){
//				if(t.isInterrupted()){
//					//throw new RuntimeException("Interrupted");
//				}
//			}
//		}, null);
//		
//		Thread t3 = new Thread(task);
//		t3.start();
//		System.out.println("start cancel ...");
//		task.cancel(true);
//		System.out.println("finishing ...");
//		task.get();
//		System.out.println("finished ...");
//		t3.join();
		
//		FutureTest test = new FutureTest();
//		System.out.println(TimeUnit.SECONDS.toNanos(5));
//		test.sleep(TimeUnit.SECONDS.toNanos(5));
//		
		
//		final SynchronousQueue<String> queue = new SynchronousQueue<String>();
//		Thread t = new Thread(()->{
//			try {
//				while(true){
//					System.out.println("get " + queue.take());
//				}
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//		
//		t.start();
//		
//		queue.put("1");
//		
//		queue.put("2");
//		
//		queue.put("3");
		
//		final ArrayBlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);
//		Thread t3 = new Thread(()->{
//			while(true){
//				try {
//					System.out.println(q2.take());
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		t3.start();
//		
//		q2.put("1");
//		q2.put("2");
//		q2.put("3");
//
//		FutureTask  f = new FutureTask(()->{
//			while(true){
//				if(Thread.currentThread().isInterrupted()){
//					throw new RuntimeException("111");
//				}
//			}
//		});
//		Thread t4 = new Thread(f);
//		
//		t4.start();
//		
//		t4.interrupt();
//		
//		f.cancel(true);
//		f.get();
		
		
		FutureTask<String> t2 = new FutureTask<>(()->{
			while(true){
				System.out.println(1);
			}
		});
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.submit(t2);
		TimeUnit.SECONDS.sleep(5);
		System.out.println(t2.cancel(true));
		System.out.println("finish ...");
	}
}
