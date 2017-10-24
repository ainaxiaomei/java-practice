package com.practice.netty;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import javax.net.ServerSocketFactory;

import io.netty.util.CharsetUtil;

public class ServerSocketTest {
	public static void main(String[] args) throws IOException {
		ServerSocket server = ServerSocketFactory.getDefault().createServerSocket(8889);
		while (true){
			Socket soc =  server.accept();
			System.out.println("connected !");
			byte [] b = new byte[1024];
			int length = soc.getInputStream().read(b);
			System.out.println("get message length : " + length);
			System.out.println("get message : " + new String(b,CharsetUtil.UTF_8));
		}
		
	}
}
