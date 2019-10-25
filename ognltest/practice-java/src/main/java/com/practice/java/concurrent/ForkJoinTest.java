package com.practice.java.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinTest {

	public static void main(String[] args) {

		ForkJoinPool pool = new ForkJoinPool();

		pool.execute(new MyTask(new int[] { 1, 5, 64568, 48, 48, 7, 98, 7, 789, 6, 4, 4, 4 }));

	}

	static class MyTask extends RecursiveAction {
		private int a[] ;

		public MyTask(int[] data) {
			this.a = data;
		}

		
	

		@Override
		protected void compute() {
			if (a.length > 5) {
				
            
			} else {
				
			}

		}

	}

}
