package com.practice.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class Encoder extends MessageToByteEncoder<String> {

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
		
		byte[] data = msg.getBytes();
		
		out.writeByte((byte)'F');
		out.writeByte((byte)data.length);
		out.writeBytes(data);
		
	}
	
}
