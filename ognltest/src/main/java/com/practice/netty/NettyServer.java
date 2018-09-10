package com.practice.netty;

import java.net.InetSocketAddress;import org.apache.commons.collections.bag.SynchronizedSortedBag;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class NettyServer {
	public static void main(String[] args) {
		
		ServerBootstrap boot = new ServerBootstrap();
		
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		
		
		boot
		.group(parentGroup, childGroup)
		.channel(NioServerSocketChannel.class)
		.childHandler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

					@Override
					public void channelActive(ChannelHandlerContext ctx) throws Exception {
						
						System.out.println(this.hashCode());
						System.out.println(" ---  connected : address - " + ctx.channel().remoteAddress());
					}

					@Override
					public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
						
						try {
							ByteBuf buf = (ByteBuf)msg;
							String str = buf.toString(io.netty.util.CharsetUtil.US_ASCII);
							System.out.println(" --- read : " + new String(str));
							
							ByteBuf out = ctx.alloc().buffer();
							
							out.writeBytes("--- received ".getBytes());
							ctx.writeAndFlush(out);
						} finally {
							ReferenceCountUtil.release(msg);
						}
						
					}

					@Override
					public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
						cause.printStackTrace();
						ctx.close();
					}
					
					
					
				});
				
			}
		});
		
		try {
			ChannelFuture future = boot.bind(new InetSocketAddress("0.0.0.0", 8585)).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}
}
