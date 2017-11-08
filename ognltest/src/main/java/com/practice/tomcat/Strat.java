package com.practice.tomcat;

import java.io.File;
import java.util.function.Predicate;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.descriptor.web.ApplicationParameter;
import org.glassfish.jersey.model.ContractProvider;
import org.glassfish.jersey.model.internal.ComponentBag;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * tomat embed简单的tomcat程序不语要复杂的配置，直接内部启动一个 tomcat，非常方便测试
 * 
 * @author vergil
 *
 */
public class Strat {
	public static void main(String[] args) throws LifecycleException, ServletException {
		String webappDirLocation = "D:\\webapp";
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8787);
		StandardContext context = (StandardContext) tomcat.addWebapp("/javaTest", webappDirLocation);
		System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

		// Declare an alternative location for your "WEB-INF/classes" dir
		// Servlet 3.0 annotation will work
		// File additionWebInfClasses = new File("target/classes");
		// WebResourceRoot resources = new StandardRoot(ctx);
		// resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
		// additionWebInfClasses.getAbsolutePath(), "/"));
		// ctx.setResources(resources);
		context.addApplicationLifecycleListener(new FirstListener());
		
		/**
		 * 异步servlet
		 */
		tomcat.addServlet("/javaTest", "asyncServlet", new AsyncServlet());
		context.addServletMapping("/async", "asyncServlet");

		/**
		 * 同步servlet
		 */
		tomcat.addServlet("/javaTest", "syncServlet", new SyncServlet());
		context.addServletMapping("/sync", "syncServlet");
		
		/**
		 * 指定jersey核心servlet
		 */
		Wrapper wrapper =  tomcat.addServlet("/javaTest", "jersey", "org.glassfish.jersey.servlet.ServletContainer");
		context.addServletMapping("/jersey/*", "jersey");
		wrapper.setAsyncSupported(true);
		/**
		 * 使用jersey包扫描,包名不能加.*不然不能识别
		 */
		wrapper.addInitParameter("jersey.config.server.provider.packages", "com.practice.tomcat");
		
		/**
		 * 直接指定一个jersey资源类
		 */
		//wrapper.addInitParameter("jersey.config.server.provider.classnames", "com.practice.tomcat.JerseyResource");
		

		tomcat.start();
		tomcat.getServer().await();

	}
}
