package com.practice.netflix.eureka;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;

public class ScheduleTaskTest {

	static class Task implements Runnable {

		private Socket soc = null;

		OutputStream ps = null;

		@Override
		public void run() {
			
			System.out.println(this.hashCode());

			if (soc == null) {
				try {
					InetAddress address = InetAddress.getByName("baidu.com");
					soc = SocketFactory.getDefault().createSocket(address, 80);

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					if (ps == null) {
						ps = soc.getOutputStream();
					}
					
					System.out.println("alive");
					ps.write("1".getBytes());
					ps.flush();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}
	
	
	public static void main(String[] args) {
		ScheduledExecutorService schedule = Executors.newScheduledThreadPool(10);
		schedule.scheduleAtFixedRate(new ScheduleTaskTest.Task(), 0, 10, TimeUnit.SECONDS);
	}

}
