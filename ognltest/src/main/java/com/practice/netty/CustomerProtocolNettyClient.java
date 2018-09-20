package com.practice.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class CustomerProtocolNettyClient {
	
	/**
	 * 
	    * @Title: main  
	    * @Description: 自定义的协议客户端，请求头两个字节，第二个字节表示长度，两个字节之后是数据 
	    * @param @param args
	    * @param @throws InterruptedException    参数  
	    * @return void    返回类型  
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Bootstrap boot = new Bootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		boot.channel(NioSocketChannel.class)
		    .group(group)
		    .handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					
					ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
						
						private ByteBuf head;
						
						private ByteBuf data;

						@Override
						public void channelActive(ChannelHandlerContext ctx) throws Exception {
							
							System.out.println("--- connected !");
							
							ByteBuf msg = ctx.alloc().buffer();
							msg.writeBytes("13".getBytes());
							ctx.writeAndFlush(msg);
						}

						@Override
							public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

								ByteBuf req = (ByteBuf) msg;
								if (data == null) {
									head.writeBytes(req);
								}

								byte dataLength = 0;
								int size = head.readableBytes();
								if (size >= 2 && data == null) {
									byte[] headByte = new byte[2];
									head.readBytes(headByte);
									dataLength = headByte[1];
									data = ctx.alloc().buffer(dataLength);
									head.readBytes(data,size-2);

								}

								if (data != null && data.readableBytes() >= dataLength) {
									data.readBytes(data);
									System.out.println(data.toString(CharsetUtil.UTF_8));
									data.release();
									data = null;

								}

							}

						@Override
						public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
							
							head = ctx.alloc().buffer(2);
						}

						@Override
						public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
							
							head.release();
							head = null;
							
							//data.release();
							//data = null;
							
							
						}
						
					});
					
				}
			})
		    .connect(new InetSocketAddress("192.168.0.116", 9999))
		    .sync()
		    .channel()
		    .closeFuture().
		    sync();
		
	}

}
