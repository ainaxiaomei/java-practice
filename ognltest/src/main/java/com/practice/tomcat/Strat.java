package com.practice.tomcat;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

/**
 * tomat embed简单的tomcat程序不语要复杂的配置，直接内部启动一个
 * tomcat，非常方便测试
 * @author vergil
 *
 */
public class Strat {
	public static void main(String[] args) throws LifecycleException, ServletException {
		 String webappDirLocation = "G:\\vergil\\web";
	        Tomcat tomcat = new Tomcat();
	        tomcat.setPort(8787);
	        StandardContext context = (StandardContext) tomcat.addWebapp("/javaTest", webappDirLocation);
	        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

	        // Declare an alternative location for your "WEB-INF/classes" dir
	        // Servlet 3.0 annotation will work
//	        File additionWebInfClasses = new File("target/classes");
//	        WebResourceRoot resources = new StandardRoot(ctx);
//	        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
//	                additionWebInfClasses.getAbsolutePath(), "/"));
//	        ctx.setResources(resources);
	    	context.addApplicationLifecycleListener(new FirstListener());
			tomcat.addServlet("/javaTest", "asyncServlet", new AsyncServlet());
			context.addServletMapping("/async", "asyncServlet");
			
			tomcat.addServlet("/javaTest", "syncServlet", new SyncServlet());
			context.addServletMapping("/sync", "syncServlet");
	        tomcat.start();
	        tomcat.getServer().await();

	}
}
