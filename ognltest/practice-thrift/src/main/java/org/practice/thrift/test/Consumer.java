package org.practice.thrift.test;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TSocket;

public class Consumer {
	
	public static void main(String[] args) throws TException {
		
		TSocket soc = new TSocket("127.0.0.1",9999);
		soc.setTimeout(1000);
		soc.open();
		TBinaryProtocol t  = new TBinaryProtocol(soc);
		
		TMultiplexedProtocol p = new TMultiplexedProtocol(t, "geeting");
		GreetingRPCService.Client client = new GreetingRPCService.Client(p);
		
		System.out.println(client.greet("1111"));
	}

}
