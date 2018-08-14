package com.practice.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import org.junit.Test;

import com.practice.netty.channelhadnler.HttpHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClientTest {

	@Test
	public void test() throws InterruptedException {
		Bootstrap boot = new Bootstrap();
		NioEventLoopGroup group = new NioEventLoopGroup();
		boot.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new HttpHandler());
			}
		}).group(group).channel(NioSocketChannel.class).connect(new InetSocketAddress("220.181.57.217", 80)).sync();

	}

	@Test
	public void NIOServier() throws InterruptedException, IOException {

		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.bind(new InetSocketAddress("127.0.0.1", 8787));

		Selector selector = Selector.open();

		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		boolean running = true;

		while (running) {

			if (selector.select() == 0) {
				continue;
			}

			Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
			while (itr.hasNext()) {
				SelectionKey selectionKey = itr.next();

				if (selectionKey.isAcceptable()) {
					itr.remove();
					System.out.println(" -- 新连接");
					ServerSocketChannel serverCh = (ServerSocketChannel) selectionKey.channel();
					java.nio.channels.SocketChannel soCh = serverCh.accept();
					soCh.configureBlocking(false);
					soCh.register(selector, SelectionKey.OP_READ);

				}

				if (selectionKey.isReadable()) {
					itr.remove();
					System.out.println(" -- 可读");

					java.nio.channels.SocketChannel soCh = (java.nio.channels.SocketChannel) selectionKey.channel();

					ByteBuffer dst = ByteBuffer.allocate(1024);
					soCh.read(dst);
					dst.flip();
					System.out.println(" -- 读取消息 : " + dst.getInt());

				}

				if (selectionKey.isWritable()) {
					itr.remove();
					System.out.println(" -- 可写");

					java.nio.channels.SocketChannel soCh = (java.nio.channels.SocketChannel) selectionKey.channel();

					ByteBuffer src = ByteBuffer.allocate(1024);
					src.putInt(1);
					src.flip();
					soCh.write(src);

				}
			}

		}

	}

	@Test
	public void NIOClient() throws InterruptedException, IOException {

		Selector selector = Selector.open();
		java.nio.channels.SocketChannel socketChannel = java.nio.channels.SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8787));
		boolean runing = true;
		while (runing) {

			if (selector.select() == 0) {
				continue;
			}

			Iterator<SelectionKey> itr = selector.selectedKeys().iterator();

			while (itr.hasNext()) {
				SelectionKey selectionKey = itr.next();
				if (selectionKey.isConnectable()) {
					itr.remove();
					if (socketChannel.finishConnect()) {

						System.out.println(" --- connected !");
						java.nio.channels.SocketChannel soCh = (java.nio.channels.SocketChannel) selectionKey.channel();
						System.out.println(soCh.equals(socketChannel));
						soCh.register(selector, SelectionKey.OP_READ);

						ByteBuffer bf = ByteBuffer.allocate(1024);
						bf.putInt(1000);
						bf.flip();
						soCh.write(bf);
					}

				}

				if (selectionKey.isReadable()) {
					itr.remove();
					System.out.println(" --- readable !");

					java.nio.channels.SocketChannel soCh = (java.nio.channels.SocketChannel) selectionKey.channel();

					ByteBuffer dst = ByteBuffer.allocate(1024);
					soCh.read(dst);
					dst.flip();
					System.out.println(" -- 读取消息 : " + dst.getInt());

				}

				if (selectionKey.isWritable()) {
					itr.remove();
					System.out.println(" --- writable !");

				}
			}

		}

	}

}
