package com.practice.netty.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelInboundHandler2 extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		System.out.println(" --- ChannelInboundHandler2 read before!");
		ctx.fireChannelRead(msg);
		System.out.println(" --- ChannelInboundHandler2 read after!");
	}
	
	

}
