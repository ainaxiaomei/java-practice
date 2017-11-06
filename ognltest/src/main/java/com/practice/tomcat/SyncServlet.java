package com.practice.tomcat;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 同步的servlet,测试在servlet新开一个线程执行业务代码会怎样
 * @author vergil
 *
 */
public class SyncServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Start Sync !");
		
		new Thread(new Task(res)).start();
	
	}
	
	static class Task implements Runnable{
		
		private HttpServletResponse res;

		
		public Task(HttpServletResponse res) {
			super();
			this.res = res;
		}


		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(2);
				System.out.println(Thread.currentThread() + " " + res.toString() + "In Sync ...");
				res.getWriter().println("Hello Sync ! " + Thread.currentThread() + " " + res.toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	

}
