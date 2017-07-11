package com.practice.rxjavatest;

import io.reactivex.Flowable;

public class RxJavaExceptionTest {
	public static void main(String[] args) {
		
//		//subscribe出现异常如果没有异常处理会抛出
//		//会中断流的发生
//		Flowable.range(0, 10)
//		.subscribe((item)->{
//			System.out.println(String.format("#1: %s",item));
//			throw new RuntimeException("error");
//			
//		});
//		
//		//subscribe出现异常调用subscribe的onError
//		//会中断流的发生
//		Flowable.range(0, 10)
//		.subscribe((item)->{
//			System.out.println(String.format("#1: %s",item));
//			throw new RuntimeException("error");
//		},(ex)->{
//			System.out.println(ex);
//		});
//		
//		//在subscribe之前出现异常
//		//不会再执行subscrib直接执行onerror
//		Flowable.range(0, 10)
//		.map((item)->{
//			throw new RuntimeException("error");
//		})
//		.subscribe((item)->{
//			System.out.println(String.format("#1: %s",item));
//			
//		},(ex)->{
//			System.out.println("in error handling");
//			System.out.println(ex);
//		});
		
		/**
			 *在subscribe之前出现异常
			 *并使用了onErrorReturn
			 * 直接执行onErrorReturn,并且会执行subscribe方法
			 *subscribe取到的值为onErrorReturn的返回值
			 *并且会终止流发生
		 */
		Flowable.range(0, 10)
		.map((item)->{
			throw new RuntimeException("error");
		})
		.onErrorReturn((item)->{
			System.out.println("in onErrorReturn");
			return "error return";
		})
		.subscribe((item)->{
			System.out.println(String.format("#1: %s",item));
			
		},(ex)->{
			System.out.println("in error handling");
			System.out.println(ex);
		});
		
	}
}
