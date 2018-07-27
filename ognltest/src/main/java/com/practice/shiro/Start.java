package com.practice.shiro;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Start {
	
	public static void main(String[] args) throws LifecycleException, ServletException {
		
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8088);
		
		tomcat.getHost().setAppBase("E:\\workspace\\java-practice\\ognltest\\src\\main\\java\\com\\practice\\shiro\\resource");
		
		Context context = tomcat.addWebapp("/shiro", "E:\\workspace\\java-practice\\ognltest\\src\\main\\java\\com\\practice\\shiro\\resource\\shiro");
		System.out.println(context);
		tomcat.start();
		tomcat.getServer().await();
		
	}

}
