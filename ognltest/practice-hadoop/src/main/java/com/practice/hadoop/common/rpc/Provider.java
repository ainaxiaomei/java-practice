package com.practice.hadoop.common.rpc;

import java.io.IOException;

import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

public class Provider {
	
	public static void main(String[] args) throws HadoopIllegalArgumentException, IOException {
		
		Configuration conf = new Configuration() ;
		Server server = new RPC.Builder(conf)
				.setBindAddress("0.0.0.0")
				.setPort(9999)
				.setProtocol(IGreeting.class)
				.setInstance(new GreetingImpl())
				.build();
		
		server.start();
		
	}

}
