package com.practice.java.rxjava;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.scheduling.annotation.Schedules;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTest1 {

	/**
	 * zipwith把两条流通过计算合并为一条流
	 * 最终还是通过zip实现
	 * 
	 * zipwith是实例方法
	 */
	@Test
	public void zipWitTest() {
		Flowable.range(1, 5)
		.zipWith(Flowable.range(6, 5), (a,b)->{
			return a + b;
		}).subscribe(System.out::println);
		
	}
	
	
	/**
	 * 只对参数中的两个流做合并，原始流会被忽略
	 * zip是静态方法
	 */
	@Test
	public void zipTest(){
		//Flowable类调用静态方法
		Flowable.zip(Flowable.range(1, 5),Flowable.range(6, 5), ( a,  b)->{return a + b;})
				.subscribe(System.out::println);
		
		//Flowable实例调用静态方法,实例数据会被忽略
		Flowable.range(1, 5).zip(Flowable.range(1, 5),Flowable.range(6, 5), ( a,  b)->{return a + b;})
		.subscribe(System.out::println);
	}
	
	/**
	 * subscribe是异步的会立即返回
	 */
	@Test
	public void subsrcibeTest(){
		Flowable.create(new FlowableOnSubscribe<String>() {

			@Override
			public void subscribe(FlowableEmitter<String> e) throws Exception {
				//睡眠两秒再产生数据测试subscribe是不是立即返回
				TimeUnit.SECONDS.sleep(5);
				e.onNext("one");
			}
		}, BackpressureStrategy.BUFFER)
		.observeOn(Schedulers.newThread())
		.subscribeOn(Schedulers.io())
		.subscribe((element)->{
			System.out.println(element);
		});
		
		System.out.println("结束啦 ...");

	}
	
	@Test
	/**
	 * 测试如何使发布和订阅在不同线程执行
	 * 1.默认情况下发布和订阅在统一线程也就是主线程执行
	 * 
	 */
	public void ThreadTest() throws InterruptedException{
		
		System.out.println("---默认测试开始---");
		System.out.println("主线程 : " + Thread.currentThread());
		
		Flowable.create(new FlowableOnSubscribe<String>() {

			@Override
			public void subscribe(FlowableEmitter<String> e) throws Exception {
			    //默认的发布线程
				System.out.println("默认的发布线程 : " + Thread.currentThread());
				e.onNext("one");
			}
		}, BackpressureStrategy.BUFFER)
			.subscribe((e)->{
				//默认的订阅线程
				System.out.println("默认的订阅线程 : " + Thread.currentThread());
		});
		
		System.out.println("---默认测试结束---");
		
		//使用observeron使观察者新开一个线程
		
		System.out.println("---observerOn测试开始---");
		Flowable.create(new FlowableOnSubscribe<String>() {

			@Override
			public void subscribe(FlowableEmitter<String> e) throws Exception {
			    //默认的发布线程
				System.out.println("默认的发布线程 : " + Thread.currentThread());
				e.onNext("one");
			}
		}, BackpressureStrategy.BUFFER)
		    .observeOn(Schedulers.newThread())
		    .subscribe((e)->{
				//默认的订阅线程
				System.out.println("默认的订阅线程 : " + Thread.currentThread());
		});
		
		//使用subscribeon使观察者新开一个线程
		TimeUnit.SECONDS.sleep(2);
		System.out.println("---subscribeOn测试开始---");
		Flowable.create(new FlowableOnSubscribe<String>() {
			
			@Override
			public void subscribe(FlowableEmitter<String> e) throws Exception {
	
				
				
				//默认的发布线程
				System.out.println("默认的发布线程 : " + Thread.currentThread());
				e.onNext("one");
			}
		}, BackpressureStrategy.BUFFER)
		.subscribeOn(Schedulers.newThread())
		.subscribe((e)->{
			//默认的订阅线程
			System.out.println("默认的订阅线程 : " + Thread.currentThread());
		});
		
		
		
	}

}
