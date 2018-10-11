package com.practice.netty.pipeline;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Bootstrap boot = new Bootstrap();
		boot.group(new NioEventLoopGroup())
		    .channel(NioSocketChannel.class)
		    .handler(new ChannelInitializer<SocketChannel>() {

				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ChannelOutboundHander1());
					ch.pipeline().addLast(new ChannelOutboundHander2());
					ch.pipeline().addLast(new ChannelOutboundHander3());
					
					ch.pipeline().addLast(new ChannelInboundHandler1());
					ch.pipeline().addLast(new ChannelInboundHandler2());
					ch.pipeline().addLast(new ChannelInboundHandler3());
					
					
				}
			});
		boot.connect(new InetSocketAddress("192.168.0.116", 9999)).sync();
		
		
	}

}
