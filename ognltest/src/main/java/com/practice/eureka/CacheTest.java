package com.practice.eureka;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

public class CacheTest {

	private Queue<Socket> cache;

	public CacheTest() throws UnknownHostException, IOException, InterruptedException {
		this.cache = new LinkedList<>();

		for (int i = 0; i < 3; i++) {

			try {
				Socket soc = new Socket("127.0.0.1", 8011);
				cache.offer(soc);

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		while (true) {
			for (int i = 0; i < 3; i++) {

				Socket soc = cache.poll();
				try {
					soc.getOutputStream().write("1".getBytes());
					System.out.println("alive ...");
					cache.offer(soc);
				} catch (IOException e) {
					System.out.println("die ...");
					Socket socket = new Socket("127.0.0.1", 8011);
					cache.offer(socket);
					e.printStackTrace();
				}
			}
			
			Thread.sleep(5000);
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException {

		new Thread(() -> {

			try {
				ServerSocket soc = new ServerSocket();
				soc.bind(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 8011));
				while (true) {
					Socket socket = soc.accept();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

		new CacheTest();

	}
}
