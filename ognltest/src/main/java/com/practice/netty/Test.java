package com.practice.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;

public class Test {
	
	
	public static void main(String[] args)  {
		
		ServerBootstrap boot = new ServerBootstrap();
	    NioEventLoopGroup boss = new NioEventLoopGroup();
	    NioEventLoopGroup worker = new NioEventLoopGroup();
	    
	    boot.group(boss,worker)
	    	.channel(NioServerSocketChannel.class)
	    	.childHandler(new ChannelInitializer<Channel>() {

				@Override
				protected void initChannel(Channel ch) throws Exception {
					ch.pipeline().addLast(new HttpRequestDecoder());
				}
			});
	    
		try {
			ChannelFuture ch = boot.bind("0.0.0.0", 8888)
				.sync();
			
			ch.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
		


}
