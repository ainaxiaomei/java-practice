package com.practice.java.rxjava;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class FirstRxJava {
	public static void main(String[] args) throws InterruptedException {
		Flowable.range(0, 10).subscribe(System.out::print);
		System.out.println();
		Flowable.fromArray("a","b","c").subscribe(System.out::print);
		System.out.println();
		
		System.out.println(Thread.currentThread());
		Flowable.fromCallable(()->{
			System.out.println(Thread.currentThread());
			return "callable";
		}).subscribeOn(Schedulers.io()).subscribe(System.out::print);
		
		
		//Flowable.interval(2, TimeUnit.SECONDS).subscribe(System.out::print);
		
		System.out.println();
		
		Flowable.range(0, 10).observeOn(Schedulers.newThread()).subscribe((item)->{
			System.out.println(Thread.currentThread());
		});
		
		//测试是不是默认发布者和订阅者在同一线程
		Flowable.create((emitter)->{
			for (int i = 0; i < 5; i++) {
				System.out.println(String.format("发送者线程%s",Thread.currentThread()));
				emitter.onNext("hello");
			}
			
		}, BackpressureStrategy.ERROR).subscribe((item)->{
			System.out.println(item);
			System.out.println(String.format("接收者线程%s",Thread.currentThread()));
		});
		
		//测试subscribeOn和ObserverOn
		Flowable.create((emitter)->{
			for (int i = 0; i < 5; i++) {
				System.out.println(String.format("发送者线程%s",Thread.currentThread()));
				emitter.onNext("hello");
			}
			
		}, BackpressureStrategy.ERROR)
		.subscribeOn(Schedulers.io())
		.observeOn(Schedulers.computation())
		.subscribe((item)->{
			System.out.println(item);
			System.out.println(String.format("接收者线程%s",Thread.currentThread()));
		});
		
		
		
		//map 测试
		
		Flowable.range(1, 20).map((item)->{
			return item * item;
		}).subscribe(System.out::println);
		
		//flatMap测试
		
		Flowable.range(1, 10).flatMap((item)->{
			return Flowable.just(item);
		}).subscribe((item)->{
			System.out.println(item.getClass());
		});
		
		
		
		try {
			throw new RuntimeException("123");
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}
		
		
		//Thread.sleep(10000);
	}
}
