package com.practice.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;

public class SocketTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		Socket soc = SocketFactory.getDefault().createSocket();
		soc.connect(new InetSocketAddress("127.0.0.1", 8889));
		OutputStream out = soc.getOutputStream();
		
		for(int i=0 ; i<502 ; i++){
			byte [] b = new byte []{0b111,0b1011};
			out.write(b);
			out.flush();
			TimeUnit.MILLISECONDS.sleep(2000);
		}
	}
}
