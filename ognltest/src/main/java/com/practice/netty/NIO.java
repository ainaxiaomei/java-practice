package com.practice.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIO {
	
	class MyException extends Exception{
		
	}
	
	public static void a () throws MyException {
		
	}
	
	public static void main(String[] args) throws IOException {
		
		
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		channel.bind(new InetSocketAddress("192.168.0.98", 7777));
		
		
		Selector select = Selector.open();
		channel.register(select, SelectionKey.OP_ACCEPT);
		
		System.out.println(" --- start ");
		while (true) {
			select.select(1000);
			
			Iterator<SelectionKey> itr = select.selectedKeys().iterator();
			
			while (itr.hasNext()) {
				SelectionKey key = itr.next();
				itr.remove();
				if (key.isAcceptable()) {
					
					System.out.println(" --- accept ");
					ServerSocketChannel ser = (ServerSocketChannel)key.channel();
					SocketChannel soc = ser.accept();
					soc.configureBlocking(false);
					soc.register(select, SelectionKey.OP_READ);
				}
				
				if (key.isReadable()) {
					System.out.println(" --- read ");
					SocketChannel soc = (SocketChannel)key.channel();
					ByteBuffer buf= ByteBuffer.allocate(1024);
					soc.read(buf);
					buf.flip();
					byte b[] = new byte[buf.remaining()];
					buf.get(b);
					
					System.out.println(new String(b,"UTF-8"));
					
					
				}
			}
		}
		
		
		
		
	}

}
