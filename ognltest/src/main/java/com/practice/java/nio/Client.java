package com.practice.java.nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
	public static void main(String[] args) throws IOException, InterruptedException {
		Socket socket  = new Socket();
		socket.connect(new InetSocketAddress("127.0.0.1", 8787));
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		pw.print("hello");
		pw.flush();
		socket.close();
		
	}
}
