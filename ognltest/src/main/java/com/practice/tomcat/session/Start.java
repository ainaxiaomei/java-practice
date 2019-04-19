package com.practice.tomcat.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Start {
	
	
	public static void main(String[] args) throws LifecycleException, ServletException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8888);
		Context context = tomcat.addWebapp("/session", "D:\\sunqi\\web");
		Tomcat.addServlet(context, "sessionServlet", "com.practice.tomcat.session.Start.SessionServlet");
		context.addServletMapping("/session/test", "sessionServlet");
		
		tomcat.start();
		tomcat.getServer().await();
	}
	
    class SessionServlet extends HttpServlet {

		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			super.service(req, resp);
		}
    	
    }

}
