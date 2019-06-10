package org.practice.thrift;

import java.net.InetSocketAddress;

import org.apache.thrift.TException;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;




public class ThriftTest {
	
	public static void main(String[] args) throws InterruptedException, TException {
		ThriftTest test = new ThriftTest();
		
		new Thread(()-> {
			try {
				test.init();
			} catch (TTransportException e) {
				e.printStackTrace();
			}
		}).start();
	
		test.consumerTest();
		
	}
	
	public void init() throws TTransportException {
		
		TServerTransport transport = new TServerSocket(new InetSocketAddress("0.0.0.0", 9871));
		
		TMultiplexedProcessor processor = new TMultiplexedProcessor();
		processor.registerProcessor("greetingService", 
				new org.practice.thrift.GreetingRPCService.Processor<Greetingimpl>(new Greetingimpl()));
		
		TServer server = new TThreadPoolServer(new Args(transport).processor(processor));
		server.serve();
		
	}
	
	public void consumerTest() throws InterruptedException, TException {
		
		TSocket soc = new TSocket("0.0.0.0", 9871);
		soc.setTimeout(1000);
		soc.open();
		TBinaryProtocol protocol = new TBinaryProtocol(soc);
		TMultiplexedProtocol p = new TMultiplexedProtocol(protocol, "greetingService");
		GreetingRPCService.Client client = new GreetingRPCService.Client(p);
		String str = client.greet("cpt");
		System.out.println(" thrift response " +  str);
		
		
		
	}

}
