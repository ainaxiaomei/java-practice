package com.practice.java.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 使用forkjoin计算1加到1000的值
 * 每100个数分成一个小任务相加
 * @author vergil
 *
 */
public class ForJoinTest {

	static class MyTask extends RecursiveTask<Integer> {

		private int start;

		private int end;

		private int sum;

		public MyTask(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		protected Integer compute() {
			if (end - start >= 100) {
				MyTask mytask1 = new MyTask(start, (end + start) / 2);
				MyTask mytask2 = new MyTask((end + start) / 2 + 1, end);
				mytask1.fork();
				mytask2.fork();
				sum = mytask1.join() + mytask2.join();
				return sum ;
			} else {
				for (int i = start; i <= end; i++) {
					sum = i + sum;
				}
				return sum;
			}

		}

	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = (ForkJoinPool) Executors.newWorkStealingPool();
		ForkJoinTask<Integer> result = pool.submit(new MyTask(1, 1000));
		System.out.println("从1加到1000的结果是: " + result.get());
	}
}
