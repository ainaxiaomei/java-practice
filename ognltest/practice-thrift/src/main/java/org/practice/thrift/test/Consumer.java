package org.practice.thrift.test;

import java.io.IOException;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TSocket;

public class Consumer {
	
	public static void main(String[] args) throws TException, IOException {
		
		TSocket soc = new TSocket("127.0.0.1",9999);
		soc.setTimeout(2000);
		soc.open();
		TBinaryProtocol t  = new TBinaryProtocol(soc);
		
		TMultiplexedProtocol p = new TMultiplexedProtocol(t, "geeting");
		GreetingRPCService.Client client = new GreetingRPCService.Client(p);
		System.out.println("--- 开始同步调用");
		System.out.println(client.greet("1111"));
		System.out.println("--- 结束同步调用");
		
		System.out.println("--- 开始异步调用");
		// 异步调用管理器
        TAsyncClientManager clientManager = new TAsyncClientManager();
        // 客户端应该使用非阻塞 IO
        TNonblockingTransport transport = new TNonblockingSocket("127.0.0.1",9999);
        // 协议与服务端需要一致
        TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();
		GreetingRPCService.AsyncClient asyncClient = new GreetingRPCService
				.AsyncClient(tProtocolFactory,clientManager,transport);
		asyncClient.greet("hello", new AsyncMethodCallback<String>() {
			
			@Override
			public void onError(Exception exception) {
				
				System.out.println("--- onError");
				
			}
			
			@Override
			public void onComplete(String response) {
				
				System.out.println("--- onComplete " + response);
				
			}
		});
		System.out.println("--- 结束异步调用");
	}

}
