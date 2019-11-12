package org.practice.thrift.test;

import java.net.InetSocketAddress;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

public class ServiceProvider {
	
	public static void main(String[] args) throws TTransportException {
		
		
		TServerSocket transport = new TServerSocket(new InetSocketAddress("0.0.0.0", 9999));
		Args arg = new Args(transport);
		
		TMultiplexedProcessor processor = new TMultiplexedProcessor();
		
		processor.registerProcessor("geeting", 
				new GreetingRPCService.Processor<GreetingRPCServiceImpl>(new GreetingRPCServiceImpl()));
		
		arg.processor(processor);
		TThreadPoolServer server = new TThreadPoolServer(arg);
		server.serve();
		
	}

}
