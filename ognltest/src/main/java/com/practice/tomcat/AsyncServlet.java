package com.practice.tomcat;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported=true)
public class AsyncServlet extends HttpServlet {

	/**
	 * 异步servlet可以把耗时的工作隔离到业务线程执行
	 * 而不需要占用容器的线程。使用request.startAsync()
	 * 获取AsyncContext
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 ExecutorService exector = (ExecutorService) req.getServletContext().getAttribute("BUSSINESS_EXECTOR");
		 
		 System.out.println(req.getServletContext().getClass());
		 System.out.println(req.getSession().getClass());
		 AsyncContext asyncContext = req.startAsync();
		 asyncContext.setTimeout(5000);
		 asyncContext.addListener(new AsyncListener() {
			
			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				System.out.println("Timeout !");
			}
			
			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {
				System.out.println("StartAsync !");
			}
			
			@Override
			public void onError(AsyncEvent event) throws IOException {
				System.out.println("Error !");
			}
			
			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				System.out.println("Complete !");
				
			}
		});
		 exector.submit(new Task(asyncContext));
	}
	
	
	static class Task implements Runnable{
		
		private AsyncContext asyncContext;
		
		public Task(AsyncContext asyncContext) {
			super();
			this.asyncContext = asyncContext;
		}



		@Override
		public void run() {
			System.out.println("Start Async Processing ...");
			
			try {
				TimeUnit.SECONDS.sleep(2);
				asyncContext.getResponse().getWriter().println("Hello Async !");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				asyncContext.complete();
			}
		}
		
	}

	
}

