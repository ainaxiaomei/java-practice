package com.practice.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
	
	public static void main(String[] args) {
		ServerSocketChannel serverChannel = null ;
		try {
			serverChannel  = ServerSocketChannel.open();
			
			serverChannel.bind(new InetSocketAddress("0.0.0.0", 8888));
			serverChannel.configureBlocking(false);
			
			Selector select = Selector.open();
			
			serverChannel.register(select, SelectionKey.OP_ACCEPT);

            for(;;) {
            	
            	if (select.select() > 0 ) {
            		
            		Iterator<SelectionKey> itr = select.selectedKeys().iterator();
            		
            		while (itr.hasNext()) {
            			SelectionKey key = itr.next();
            			
            			if (key.isAcceptable()) {
            				ServerSocketChannel soc = (ServerSocketChannel) key.channel();
            				SocketChannel client = soc.accept();
            				client.configureBlocking(false);
            				client.register(select, SelectionKey.OP_READ);
            				System.out.println("--- connect");
            				itr.remove();
            				
            			}
            			
            			if (key.isReadable()) {
            				System.out.println("--- read");
            				
            				SocketChannel client = (SocketChannel) key.channel();
            				itr.remove();
            				
            			}
            			
            		}
            		
            	}
            }
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
