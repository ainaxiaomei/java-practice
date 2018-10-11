package com.practice.netty.pipeline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelInboundHandler1 extends ChannelInboundHandlerAdapter {
	

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		System.out.println(" --- ChannelInboundHandler1 read before!");
		ctx.fireChannelRead(msg);
		System.out.println(" --- ChannelInboundHandler1 read after!");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		ByteBuf data = ctx.alloc().buffer();
		data.writeBytes("sync".getBytes());
		ctx.writeAndFlush(data);
	
	}
	
	

}
