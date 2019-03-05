package com.practice.netflix.hystrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;

import rx.Observable;

public class HystrixTest {
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		System.out.println(" --- 主线程 " + Thread.currentThread());
		HelloCommand command = new HelloCommand("commonCommand");
		String str = command.execute();
		System.out.println(str);
		
		
		HelloObserverCommand observerCommand = new HelloObserverCommand("observerCommand");
		observerCommand.observe().subscribe((value)->{
			System.out.println(value);
		});
		
		
	}
	


}

class HelloCommand extends HystrixCommand<String> {
	
	private String name;
	
	public HelloCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey(name));
		
		this.name = name;
	}




	@Override
	protected String run() throws Exception {
		
		System.out.println(" --- HelloCommand  调用线程 " + Thread.currentThread());
		TimeUnit.SECONDS.sleep(30);
		return "Hello " + name +  " , Nice to meet you !";
	}




	@Override
	protected String getFallback() {
		return "fallback";
	}
	
	
	
}

class HelloObserverCommand extends HystrixObservableCommand<String> {
	
	private String name ;

	protected HelloObserverCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey(name));
		this.name = name;
	}

	@Override
	protected Observable<String> construct() {
		
		System.out.println(" --- HelloObserverCommand  调用线程 " + Thread.currentThread());

		return Observable.just("Hello " + name +  " , Nice to meet you !");
	}
	
}
