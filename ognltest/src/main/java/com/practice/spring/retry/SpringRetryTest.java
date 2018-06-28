package com.practice.spring.retry;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class SpringRetryTest {

	static int doSth() {

		System.out.println("-- do !");
		throw new RuntimeException("error");

	}

	public static void main(String[] args) throws Exception {
		RetryTemplate template = new RetryTemplate();
		TimeoutRetryPolicy policy = new TimeoutRetryPolicy();
		policy.setTimeout(30000L);
		template.setRetryPolicy(policy);
		template.execute(new RetryCallback<Integer, Exception>() {

			@Override
			public Integer doWithRetry(RetryContext context) throws Exception {
				return SpringRetryTest.doSth();
			}
		});
	}

}
