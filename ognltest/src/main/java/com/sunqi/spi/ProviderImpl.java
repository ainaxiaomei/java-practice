package com.sunqi.spi;

import snail.ognltest.SPIProvider;

public class ProviderImpl implements SPIProvider {

	@Override
	public String provide() {
		return "SPI Provider";
	}

}
