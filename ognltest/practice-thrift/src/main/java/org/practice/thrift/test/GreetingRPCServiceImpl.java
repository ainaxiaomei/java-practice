package org.practice.thrift.test;

import org.apache.thrift.TException;

public class GreetingRPCServiceImpl implements GreetingRPCService.Iface{

	@Override
	public String greet(String word) throws TException {
		return "hello";
	}

}
