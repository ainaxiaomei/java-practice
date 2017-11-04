package com.practice.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec.ButtonType;
import org.junit.Test;

import ch.qos.logback.classic.net.server.ServerSocketAppender;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Netty1 {

	@Test
	public void testServer() {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap boot = new ServerBootstrap();
		ChannelFuture channelFuture;
		try {
			channelFuture = boot.channel(NioServerSocketChannel.class).group(boss, worker)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new DiscardHandler());
						}
					}).bind(8888).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}

	}

	@Test
	public void testClient() {
		Bootstrap boot = new Bootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ChannelFuture f = boot.group(group).handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("bufferHandler", new BufferClinetHandler());
					ch.pipeline().addLast("TimeHandler", new TimeClientHandler());
				}
			}).channel(NioSocketChannel.class).connect("10.220.10.31", 8888).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
			group.shutdownGracefully();
		}
	}

	@Test
	public void testNIO() {

		try {
			Selector sel = Selector.open();
			boolean flag = true;
			java.nio.channels.SocketChannel channel = java.nio.channels.SocketChannel.open();
			channel.configureBlocking(false);
			channel.register(sel, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			channel.connect(new InetSocketAddress(8888));
			while (flag) {
				if (sel.select() == 0)
					continue;

				Iterator<SelectionKey> itr = sel.selectedKeys().iterator();
				while (itr.hasNext()) {
					SelectionKey key = itr.next();
					if (key.isConnectable()) {
						itr.remove();
						System.out.println("connectable !");
						// System.out.println(channel==key.channel());//true
						channel.finishConnect();
					}

					if (key.isAcceptable()) {
						System.out.println("accpeted!");
					}

					if (key.isReadable()) {
						System.out.println("start read ...");
						java.nio.channels.SocketChannel soc = (java.nio.channels.SocketChannel) key.channel();
						ByteBuffer dst = ByteBuffer.allocate(4);
						soc.read(dst);
						dst.flip();
						System.out.println(dst);
					}

					if (key.isWritable()) {
						System.out.println("start write ...");
						java.nio.channels.SocketChannel soc = (java.nio.channels.SocketChannel) key.channel();
						ByteBuffer dst = ByteBuffer.allocate(4);
						dst.putInt(123213);
						dst.flip();
						soc.write(dst);

					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNIO2() throws IOException {
		java.nio.channels.SocketChannel channel = java.nio.channels.SocketChannel.open();
		channel.configureBlocking(false);
		Selector selector = Selector.open();

		if (channel.connect(new InetSocketAddress(8989))) {
			channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		} else {
			channel.register(selector, SelectionKey.OP_CONNECT);
		}

		while (true) {
			selector.select();
			Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
			while (itr.hasNext()) {
				SelectionKey key = itr.next();
				itr.remove();

				if (key.isConnectable()) {
					java.nio.channels.SocketChannel chan = (java.nio.channels.SocketChannel) key.channel();
					if (chan.finishConnect()) {
						System.out.println("connected !");
						chan.register(selector, SelectionKey.OP_READ );
						ByteBuffer buf = ByteBuffer.allocate(1024);
						buf.put("1234".getBytes());
						buf.flip();
						chan.write(buf);

					} else {
						System.out.println("连接失败!");
					}
				}

				if (key.isWritable()) {
					System.out.println("writable !");

				}

				if (key.isReadable()) {

					System.out.println("readable !");

				}
			}

		}

	}

	@Test
	public void NIOServerTest() throws IOException {
		java.nio.channels.ServerSocketChannel serverChannel = java.nio.channels.ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.bind(new InetSocketAddress(8989));
		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		boolean flag = true;
		while (flag) {
			selector.select(1000);
			Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
			while (itr.hasNext()) {
				SelectionKey key = itr.next();
				itr.remove();
				if (key.isAcceptable()) {
					System.out.println("新连接 ...");
					java.nio.channels.ServerSocketChannel sch = (java.nio.channels.ServerSocketChannel) key.channel();
					java.nio.channels.SocketChannel ch = sch.accept();
					ch.configureBlocking(false);
					ch.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

				}

				if (key.isReadable()) {
					System.out.println("Readable ...");
					java.nio.channels.SocketChannel ch = (java.nio.channels.SocketChannel) key.channel();
					ByteBuffer dst = ByteBuffer.allocate(1024);
					if (ch.read(dst) > 0) {
						dst.flip();
						System.out.println("收到消息 : " + dst.toString());
					}else {
						System.out.println("未读到数据 ...");
					}
						
				}

				if (key.isWritable()) {
					System.out.println("Writable ...");
				}
			}

		}

	}

}
