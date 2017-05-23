package snail.ognltest;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class DiscardServer {

	public static void main(String[] args) throws InterruptedException {
		ServerBootstrap boot = new ServerBootstrap();
		EventLoopGroup master = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ChannelFuture fu = boot.channel(NioServerSocketChannel.class)
		.group(master,worker)
		.childHandler(new ChannelInboundHandlerAdapter(){

			@Override
			public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
				try {
					System.out.println(((ByteBuf)msg).toString(CharsetUtil.US_ASCII));
					ctx.writeAndFlush(msg);
				} finally {
					//((ByteBuf)msg).release();
				}
			}
			
			
		}).option(ChannelOption.SO_BACKLOG, 128)
		.childOption(ChannelOption.SO_KEEPALIVE, true)
		.bind(8888).sync();
		
		fu.channel().closeFuture().sync();
	}

}
