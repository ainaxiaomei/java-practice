package com.practice.java.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait实现一个多生产者，多消费者的模式
 * @author vergil
 *
 */
public class ProducerConsumerTest {
	
	private List<String> bucket = new ArrayList<>();
	
	private Object lock = new Object();
	
	private int count = 10;
	
	public static void main(String[] args) {
		
	}
	
	public void produce() throws InterruptedException {
		
		synchronized (lock) {
			while (bucket.size() >= count) {
				lock.wait();
			}
			
			bucket.add("1");
			lock.notifyAll();
			
		}
	}
	
	public void consumer() throws InterruptedException {
		
		synchronized (lock) {
			while (bucket.size() == 0) {
				lock.wait();
			}
			bucket.remove(0);
			lock.notifyAll();

		}
		
	}

}
