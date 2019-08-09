package org.practice.spring;

public class ProbeImpl implements Probe{

	@Override
	public String getProbeData() {
		System.out.println("获取探针...");
		return null;
	}
	
}