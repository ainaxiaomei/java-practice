package com.practice.netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import javax.net.ServerSocketFactory;

import io.netty.util.CharsetUtil;

/**
 * 
 * @author Administrator
 *  accept()阻塞线程处于runnable状态
 *  read()无数据时阻塞线程处于runnable状态
 */
public class ServerSocketTest {
	public static void main(String[] args) throws IOException {
		ServerSocket server = ServerSocketFactory.getDefault().createServerSocket(8889);
		while (true){
			final Socket soc =  server.accept();
			System.out.println("connected !");
			new Thread(()->{
				byte [] b = new byte[1024];
				int length;
				try {
					InputStream in = soc.getInputStream();
					while((length = in.read(b))> 0){
						System.out.println("get message length : " + length);
						System.out.println("get message : " + new String(b,CharsetUtil.UTF_8));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			},"Thread#" + soc.hashCode()).start();
			
		}
		
	}
}
