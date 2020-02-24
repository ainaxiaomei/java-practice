package com.practice.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Test {
	
	
	public static void main(String[] args)  {
		
		
		
	}
	
	
	static class Accptor implements Runnable {
		
		private ServerSocketChannel server;
		
		private Selector selector;
		
		public Accptor(ServerSocketChannel server,Selector selector) {
			
			this.server = server;
			this.selector = selector;
		}

		@Override
		public void run() {
			
			try {
				SocketChannel clinet = server.accept();
				clinet.configureBlocking(false);
				clinet.register(selector, SelectionKey.OP_READ,new Handler(selector));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	static class Handler implements Runnable {
		
		private Selector selector;
		
		public Handler(Selector selector) {
			
			this.selector = selector;
			
		}

		@Override
		public void run() {
			
		}
		
		
	}
	
	static class Reactor implements Runnable {
		
		private ServerSocketChannel server;
		
		private Selector selector;
		
		public Reactor() {
			
			try {
				server = ServerSocketChannel.open();
				server.configureBlocking(false);
				server.bind(new InetSocketAddress("0.0.0.0", 8888));
				selector = Selector.open();
				server.register(selector, SelectionKey.OP_ACCEPT, new Accptor(server,selector));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public void run() {
			
			try {
				while (selector.select() > 0 ) {
					
					while (selector.selectedKeys().iterator().hasNext()) {
						
						SelectionKey  key = selector.selectedKeys().iterator().next();
						Runnable r = (Runnable) (key.attachment());
						r.run();
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
		


}
