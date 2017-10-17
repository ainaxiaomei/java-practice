package com.practice.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

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
			channelFuture = boot.channel(NioServerSocketChannel.class)
				.group(boss, worker)
				.childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new DiscardHandler());
					}
				})
				.bind(8888).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
		
		
	}
	
	@Test
	public void testClient(){
		Bootstrap boot = new Bootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ChannelFuture f = boot.group(group)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast("bufferHandler",new BufferClinetHandler());
						ch.pipeline().addLast("TimeHandler", new TimeClientHandler());
					}
				})
				.channel(NioSocketChannel.class)
				.connect("10.220.10.31", 8888).sync();
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
			channel.register(sel, SelectionKey.OP_CONNECT|SelectionKey.OP_READ|SelectionKey.OP_WRITE);
			channel.connect(new InetSocketAddress(8888));
			while(flag) {
				if (sel.select() == 0) continue;
				
				Iterator<SelectionKey> itr = sel.selectedKeys().iterator();
				while(itr.hasNext()) {
					SelectionKey key = itr.next();
					if(key.isConnectable()) {
						System.out.println("connected !");
					}
					
					if(key.isReadable()) {
						System.out.println("start read ...");
						java.nio.channels.SocketChannel soc = (java.nio.channels.SocketChannel)key.channel();
						ByteBuffer dst = ByteBuffer.allocate(4);
						soc.read(dst);
						dst.flip();
						System.out.println(dst);
					}
					
					if(key.isWritable()) {
						System.out.println("start write ...");
						java.nio.channels.SocketChannel soc = (java.nio.channels.SocketChannel)key.channel();
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

}
