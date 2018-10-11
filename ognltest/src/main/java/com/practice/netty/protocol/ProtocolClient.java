package com.practice.netty.protocol;

import java.net.InetSocketAddress;
import java.util.List;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ProtocolClient {
	
	public static void main(String[] args) throws InterruptedException {
		
		Bootstrap boot = new Bootstrap();
		
		boot.group(new NioEventLoopGroup())
		    .channel(NioSocketChannel.class)
		    .handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new Decoder());
					ch.pipeline().addLast(new Encoder());
					ch.pipeline().addLast(new SimpleClientHandler());
					
					System.out.println(ch.pipeline());
				}
			})
		    .connect(new InetSocketAddress("192.168.0.116", 9999)).sync()
		    .channel().closeFuture().sync();
		    
	}
	
	public static class ClientHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			
			ChannelFuture future = ctx.writeAndFlush("sync");
			
//			future.addListener(new ChannelFutureListener() {
//
//				@Override
//				public void operationComplete(ChannelFuture future) throws Exception {
//					
//					if (future.isSuccess()) {
//						future.channel().close();
//					} else {
//						future.cause().printStackTrace();
//						future.channel().close();
//					}
//					
//				}
//				
//			});
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			
			String data = (String)msg;
			System.out.println(data);
			
		}
		
		
		

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			System.out.println("error !");
		}
	}
	
	
	public static class SimpleClientHandler extends SimpleChannelInboundHandler<String> {
		
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			
			ChannelFuture future = ctx.writeAndFlush("sync");
			
//			future.addListener(new ChannelFutureListener() {
//
//				@Override
//				public void operationComplete(ChannelFuture future) throws Exception {
//					
//					if (future.isSuccess()) {
//						future.channel().close();
//					} else {
//						future.cause().printStackTrace();
//						future.channel().close();
//					}
//					
//				}
//				
//			});
		}

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
			
			System.out.println(msg);
			
		}
		
	}
	
	
	

}
