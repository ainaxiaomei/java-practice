package com.practice.hadoop.common.rpc;

import java.io.IOException;

import org.apache.hadoop.ipc.ProtocolSignature;

public class GreetingImpl implements IGreeting {
	
	

	@Override
	public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
		return versionID;
	}

	@Override
	public ProtocolSignature getProtocolSignature(String protocol, long clientVersion, int clientMethodsHash)
			throws IOException {
		return new ProtocolSignature(versionID,null);
	}

	@Override
	public String greet() {
		return "hello";
	}

}
