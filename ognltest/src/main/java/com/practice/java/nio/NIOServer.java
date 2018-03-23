package com.practice.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

	public static void main(String[] args) throws IOException {
		ServerSocketChannel server = ServerSocketChannel.open();
		server.bind(new InetSocketAddress("127.0.0.1", 8787));
		server.configureBlocking(false);

		Selector selector = Selector.open();

		System.out.println("-- server start !");

		new Thread(() -> {

			try {
				server.register(selector, SelectionKey.OP_ACCEPT);
				while (true) {
					selector.select();
					Set<SelectionKey> keys = selector.selectedKeys();
					Iterator<SelectionKey> itr = keys.iterator();
					while (itr.hasNext()) {
						SelectionKey key = itr.next();
						itr.remove();

						if (key.isAcceptable()) {
                            
							System.out.println("-- Acceptable");
							SocketChannel soc = server.accept();
							if (soc != null) {
								soc.configureBlocking(false);
								soc.register(selector, SelectionKey.OP_READ);
							}
							
						}

						if (key.isReadable()) {
							System.out.println("-- Readable");
							SocketChannel channel = (SocketChannel) key.channel();
							ByteBuffer dst = ByteBuffer.allocate(1024);
							int read = channel.read(dst);
							if(read > 0) {
								
								dst.flip();
								byte msg[] = new byte[dst.remaining()];
								dst.get(msg);
								System.out.println(new String(msg));

							}else {
								key.cancel();
								channel.close();
								
							}
							
						}

//						if (key.isConnectable()) {
//							System.out.println("-- Connectable");
//
//						}
//
//						if (key.isWritable()) {
//							System.out.println("-- Writable");
//
//						}

					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}).start();


	}

}
