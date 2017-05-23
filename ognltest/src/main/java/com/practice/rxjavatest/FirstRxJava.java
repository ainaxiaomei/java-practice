package com.practice.rxjavatest;

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
		
		Thread.sleep(10000);
		
	}
}
