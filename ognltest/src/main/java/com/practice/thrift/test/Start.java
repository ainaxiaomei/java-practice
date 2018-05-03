package com.practice.thrift.test;

import javax.swing.plaf.multi.MultiPanelUI;

import org.apache.thrift.TException;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.junit.Test;

public class Start {

	@Test
	public void startServer() {
		TServerTransport transport;
		try {
			transport = new TServerSocket(8888);
			TMultiplexedProcessor processor = new TMultiplexedProcessor();
			processor.registerProcessor("mathService",
					new IMathService.Processor<IMathService.Iface>(new MathServiceImpl()));

			TServer server = new TThreadPoolServer(new Args(transport).processor(processor));
			System.out.println("server start ...");
			
			server.serve();
			
		} catch (TTransportException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test() {

		try {
			TTransport transport = new TSocket("localhost", 8888);
			TProtocol protocol = new TBinaryProtocol(transport);
			transport.open();
			IMathService.Client client = new IMathService.Client(new TMultiplexedProtocol(protocol,"mathService"));
			
			int res = client.add(1, 2);
			System.out.println(res);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

}
