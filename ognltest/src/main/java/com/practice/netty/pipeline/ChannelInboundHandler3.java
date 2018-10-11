package com.practice.netty.pipeline;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelInboundHandler3 extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		System.out.println(" --- ChannelInboundHandler3 read before!");
		ctx.fireChannelRead(msg);
		System.out.println(" --- ChannelInboundHandler3 read after!");
	}
	
	

}
