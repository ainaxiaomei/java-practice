package com.practice.netty.pipeline;

import javax.swing.plaf.synth.SynthSpinnerUI;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class ChannelOutboundHander1 extends ChannelOutboundHandlerAdapter {

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println(" --- ChannelOutboundHander1 write before !");
		ctx.write(msg);
		System.out.println(" --- ChannelOutboundHander1 write after !");
	}
	
	

}
