package com.practice.tomcat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class FirstListener implements ServletContextListener{
	
	private ExecutorService extcutor;

	@Override
	public void contextInitialized(ServletContextEvent sce) { 
		extcutor = Executors.newCachedThreadPool();
		sce.getServletContext().setAttribute("BUSSINESS_EXECTOR", extcutor);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		extcutor.shutdown();
	}

}
