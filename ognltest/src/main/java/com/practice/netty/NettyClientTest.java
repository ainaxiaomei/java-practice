package com.practice.netty;

import java.net.InetSocketAddress;

import org.junit.Test;

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
		}).group(group)
		  .channel(NioSocketChannel.class)
		  .connect(new InetSocketAddress( "220.181.57.217", 80))
		  .sync();
		
	}

}
