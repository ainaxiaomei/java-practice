package com.practice.java.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class NIOServerTest {

	public static void main(String[] args) throws ClosedChannelException {

		boolean running = true;
		Selector select = null;
		ServerSocketChannel server = null;
		try {
			select = Selector.open();
			server = ServerSocketChannel.open();

			server.configureBlocking(false);
			server.register(select, SelectionKey.OP_ACCEPT);

			while (running) {

				select.select();
				Iterator<SelectionKey> itr = select.selectedKeys().iterator();

				while (itr.hasNext()) {
					SelectionKey key = itr.next();
					itr.remove();
					if (!key.isValid()) {
						continue;
					}

					if (key.isAcceptable()) {

					}

					if (key.isReadable()) {

					}
					
					if (key.isWritable()) {

					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				server.close();
				select.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
