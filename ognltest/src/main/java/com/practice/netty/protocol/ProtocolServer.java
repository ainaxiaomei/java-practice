package com.practice.netty.protocol;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ProtocolServer {
	
	public static void main(String[] args) throws InterruptedException {
		
		ServerBootstrap boot = new ServerBootstrap();
		
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		
		boot.group(boss, worker)
		    .channel(NioServerSocketChannel.class)
		    .childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new Decoder());
					ch.pipeline().addLast(new Encoder());
					ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

						@Override
						public void channelActive(ChannelHandlerContext ctx) throws Exception {
							
							System.out.println(" --- connected !");
						}

						@Override
						public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
							
							String data = (String)msg;
							System.out.println(" --- receive : " + data);
							
							ctx.writeAndFlush("hello");
						}

						@Override
						public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
							cause.printStackTrace();
							System.out.println("error");
						}
						
					});
					
				}
			})
		    .bind(new InetSocketAddress("0.0.0.0", 9999))
		    .sync();
	}
	
	

}
