package com.practice.netty.channelhadnler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BufferClinetHandler extends ChannelInboundHandlerAdapter {
	
	private ByteBuf buf;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		buf.writeBytes((ByteBuf)msg);
		System.out.println("[BufferClinetHandler] response from server");
		if(buf.readableBytes() >= 4){
		   //long currentTimeMillis = (buf.readUnsignedInt() - 2208988800L) * 1000L;
           // System.out.println("[BufferClinetHandler] response from server : " + new Date(currentTimeMillis));
            ctx.fireChannelRead(buf);
            ((ByteBuf)msg).release();
            ctx.close();
		}
		
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		buf = ctx.alloc().buffer(4);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		
		buf.release();
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
        ctx.close();
	}
	
	
	

}
