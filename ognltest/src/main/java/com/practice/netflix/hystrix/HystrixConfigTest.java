package com.practice.netflix.hystrix;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;

public class HystrixConfigTest {
    
	/**
	 * @throws InterruptedException 
	 * 
	    * @Title: test  
	    * @Description: Hystrix执行策略。  Thread or Semaphore
	    * 1.Thread 每个command有一个独立的线程池
	    * 2.Semaphore 限定可以并发线程支持的次数
	    * 3.HystrixCommand默认使用（Thread）,HystrixObservableCommand默认使用（SEMAPHORE）
	    * 
	    * @param     参数  
	    * @return void    返回类型  
	    * @throws
	    * 
	 */
	@Test
	public void strategyTest() throws InterruptedException {
		
		System.out.println(" --- " + System.currentTimeMillis() + " main Thread " + Thread.currentThread());
		HystrixCommandProperties.Setter propSetter = 
				HystrixCommandProperties.Setter()
				.withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)
				.withExecutionIsolationSemaphoreMaxConcurrentRequests(1)
				.withExecutionTimeoutInMilliseconds(5000);
		
		
		Thread t1 = new Thread(()->{ 
			HystrixCommand<String> command1 = new StrategyCommand(propSetter);
			command1.execute();
			
		});
		
		t1.start();
		
		Thread t2 = new Thread(()->{ 
			HystrixCommand<String> command2 = new StrategyCommand(propSetter);
			command2.execute();
			
		});
		
		t2.start();
		
		t1.join();
		t2.join();
		
		
		
		
	}
	
	class StrategyCommand extends HystrixCommand<String>{
		

		protected StrategyCommand(HystrixCommandProperties.Setter propSetter) {
			super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("strategy"))
					.andCommandPropertiesDefaults(propSetter));
			
		}

		@Override
		protected String run() throws Exception {
			
			System.out.println(" --- " + System.currentTimeMillis() + "strategyCommand execute Thread " + Thread.currentThread());
			TimeUnit.SECONDS.sleep(3);
			return "hello";
		}
		
	};

}
