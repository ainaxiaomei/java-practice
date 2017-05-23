package com.practice.spitest;

import com.practice.ognltest.SPIProvider;

public class ProviderImpl implements SPIProvider {

	@Override
	public String provide() {
		return "SPI Provider";
	}

}
