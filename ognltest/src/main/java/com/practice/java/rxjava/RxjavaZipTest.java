package com.practice.java.rxjava;

import io.reactivex.Flowable;

public class RxjavaZipTest {
	public static void main(String[] args) {
		Flowable.range(0, 10).zipWith(Flowable.just(10, 11), (a,b)->{
			return a + b;
		}).subscribe(System.out::println);

	}
}
