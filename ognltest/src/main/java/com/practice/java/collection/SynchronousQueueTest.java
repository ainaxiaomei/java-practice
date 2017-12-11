package com.practice.java.collection;

import static org.junit.Assert.*;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * SynchronousQueue是一个特殊的阻塞队列，用在CachadThreadPoolExecutor中
 * 每一个插入操作必须等待另一个线程相应的删除操作
 * 每一个删除操作必须等待另一个线程相应的插入操作
 * @author Administrator
 *
 */
public class SynchronousQueueTest {
	
	private SynchronousQueue<String> sq;

	@Before
	public void init(){
		sq = new SynchronousQueue<String>();
	}
	
	/**
	 * 直接插入队列会出错
	 */
	@Test
	public void test() {
			
		try{
			//调用offer返回false
			System.out.println("直接offer入队: " + (sq.offer("123") ? "成功" : "失败"));
			
			//调用add会发生异常
			//System.out.println("直接add入队: " + (sq.add("123") ? "成功" : "失败"));
			
			//调用put会阻塞
			sq.put("123");
			System.out.println("使用put成功");
		}catch (Exception e){
			System.out.println("直接add入队发生异常");
		}
		
	}
	
	
	/**
	 * 一个线程使用take另一个线程使用offer
	 * 只有有正在等待取数据的另一个线程才能插入成功
	 * 只有有正在等待插入数据的另一个线程才能取数据成功
	 */
	@Test
	public void test2() {
		
		try{
			System.out.println("没有线程等待去数据时插入");
			System.out.println("直接offer入队: " + (sq.offer("123") ? "成功" : "失败"));
			new Thread(()->{
				try {
					System.out.println("开始取数据 ...");
					//String str = sq.take();//使用take等待成功
					//String str = sq.poll();//使用poll等待不行
					String str = sq.poll(5,TimeUnit.SECONDS);//使用带时间的poll等待成功
					System.out.println("完成取数据: " + str);
				} catch (Exception e) {
					e.printStackTrace();
				}
			},"take-thread").start();
			
			TimeUnit.SECONDS.sleep(2);
			System.out.println("有线程等待去数据时插入");
			System.out.println("直接offer入队: " + (sq.offer("123") ? "成功" : "失败"));
		}catch (Exception e){
			System.out.println("直接add入队发生异常");
		}
		
	}
	
	

}
