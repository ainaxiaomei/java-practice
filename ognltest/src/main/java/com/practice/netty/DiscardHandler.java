package com.practice.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class DiscardHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		
		ByteBuf buf = (ByteBuf)msg;
		String data = buf.toString(CharsetUtil.UTF_8);
		buf.release();
		System.out.println("get message : " + data);
		ChannelFuture f = ctx.writeAndFlush("get message : " + data);
		
		if("q\r\n".equals(data)){
			f.addListener(ChannelFutureListener.CLOSE);
		}
		
	}
	
	

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
//		final ByteBuf time = ctx.alloc().buffer(4); // (2)
//        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
//        
//        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
//        f.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) {
//                assert f == future;
//                ctx.close();
//            }
//        }); 
	}



	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	

}
