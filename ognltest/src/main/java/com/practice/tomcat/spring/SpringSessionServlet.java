package com.practice.tomcat.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpringSessionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("所有Filter : " + req.getServletContext());
		System.out.println("经过SessionRepositoryRequestWrapper过滤器后的request :" + req.getClass());
		
		/**
		 * 使用session。此处getSession获取到的不是javaee的httpSession对象
		 */
		System.out.println("session类型为: " + req.getSession());
		req.getSession().setAttribute("session", "1234");
		
		System.out.println("获取session,key为session: " + req.getSession().getAttribute("session"));
		resp.getWriter().println("Hello Spring Session !");
		
		
	}
	
	
	

}
