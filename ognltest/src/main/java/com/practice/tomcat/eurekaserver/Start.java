package com.practice.tomcat.eurekaserver;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;


/**
 * 使用tomcat部署war需要把docbase指定为war的全路径
 * 
 * war包的解压不是有host的appBase完成而是有context的docbase完成
 * @author vergil
 *
 */
public class Start {

	public static void main(String[] args) throws ServletException, LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.getHost().setAppBase("G:\\vergil\\web");
		Context context = tomcat.addWebapp("/eureka", "G:\\vergil\\web\\eureka.war");
		tomcat.start();
		tomcat.getServer().await();

	}

}
