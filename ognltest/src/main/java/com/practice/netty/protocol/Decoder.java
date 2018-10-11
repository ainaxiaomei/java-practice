package com.practice.netty.protocol;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class Decoder extends ByteToMessageDecoder {

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