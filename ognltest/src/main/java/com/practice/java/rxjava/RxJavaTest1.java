package com.practice.java.rxjava;

import org.junit.Test;

import io.reactivex.Flowable;

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

}
