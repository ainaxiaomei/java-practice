package com.practice.netty;

import java.net.InetSocketAddress;
import java.util.List;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ClientProrocolWithDecoder {
	
	public static void main(String[] args) throws InterruptedException {
		
		Bootstrap boot = new Bootstrap();
		
		boot.group(new NioEventLoopGroup())
		    .channel(NioSocketChannel.class)
		    .handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new Decoder());
					ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

						@Override
						public void channelActive(ChannelHandlerContext ctx) throws Exception {
							
							ByteBuf msg = ctx.alloc().buffer();
							msg.writeBytes("111".getBytes());
							ctx.writeAndFlush(msg);
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
						
					});
					
				}
			})
		    .connect(new InetSocketAddress("192.168.0.116", 9999)).sync();
		    
	}
	
	public static class Decoder extends ByteToMessageDecoder {

		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
			
			if (in.readableBytes() < 2) {
				return ;
			}
			
			in.markReaderIndex();
			
			ByteBuf header = in.readBytes(2);
			byte length = header.getByte(1);
			
			if (in.readableBytes() < length) {
				in.resetReaderIndex();
				return;
			} else {
				
				byte[] dst = new byte[length];
				in.readBytes(dst);
				String str = new String(dst);
				out.add(str);
				
			}
			
			
			
		}
		
	}

}
