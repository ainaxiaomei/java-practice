package org.practice.thrift;

import org.practice.thrift.GreetingRPCService.Iface;

public class Greetingimpl implements Iface {

	@Override
	public String greet(String word) {
		
		return "hello " + word ;
	}

}
