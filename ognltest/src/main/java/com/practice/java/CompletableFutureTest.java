package com.practice.java;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class CompletableFutureTest {

	@Test
	public void test() throws InterruptedException, ExecutionException {
		CompletableFuture<String> cf = new CompletableFuture();
		CompletableFuture<Integer> asyncCf = CompletableFuture.supplyAsync(() -> {
			for (int i = 0; i < 1000; i++) {
				System.out.println(i);
			}
			
			return 1;
		});
		

		System.out.println(asyncCf.get());
	}

}
