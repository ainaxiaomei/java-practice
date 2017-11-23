package com.practice.tomcat.spring;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ApplicationParameter;

public class Start {
	public static void main(String[] args) throws ServletException, LifecycleException {
		
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8787);
		Context context = tomcat.addWebapp("/tomcat-spring", "D:\\webapp");
		
		/**
		 * 设置spring listener
		 */
		context.addApplicationListener("org.springframework.web.context.ContextLoaderListener");
		/**
		 * 指定applicationContext
		 */
		ApplicationParameter contextClass = new ApplicationParameter();
		contextClass.setName("contextClass");
		contextClass.setValue("org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
		context.addApplicationParameter(contextClass);
		
		/**
		 * 添加springsessionservlet
		 */
		tomcat.addServlet("/tomcat-spring", "springSessionServlet", "com.practice.tomcat.spring.SpringSessionServlet");
		context.addServletMapping("/session", "springSessionServlet");
		
		/**
		 * 指定配置文件类
		 */
		ApplicationParameter contextConfigLocation = new ApplicationParameter();
		contextConfigLocation.setName("contextConfigLocation");
		contextConfigLocation.setValue("com.practice.tomcat.spring.WebConfiguration");
		context.addApplicationParameter(contextConfigLocation);
		
		tomcat.start();
		tomcat.getServer().await();
	}
}
