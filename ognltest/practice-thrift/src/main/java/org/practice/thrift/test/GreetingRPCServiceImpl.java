package org.practice.thrift.test;

import java.util.concurrent.TimeUnit;

import org.apache.thrift.TException;

public class GreetingRPCServiceImpl implements GreetingRPCService.Iface{

	@Override
	public String greet(String word) throws TException {
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "hello";
	}

}
