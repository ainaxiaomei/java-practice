package com.practice.thrift.test;

import org.apache.thrift.TException;

public class MathServiceImpl implements IMathService.Iface{

	@Override
	public int add(int key, int value) throws TException {
		return key + value;
	}

}
