package com.practice.tomcat;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Strat {
	public static void main(String[] args) throws LifecycleException, ServletException {
//		Tomcat tomcat = new Tomcat();
//		tomcat.setPort(8787);
//		tomcat.setBaseDir("D:\\wbeapp");
//		tomcat.getHost().setAutoDeploy(false);
//
//		String contextPath = "/async";
//		StandardContext context = new StandardContext();
//		context.setPath(contextPath);
//		context.addApplicationEventListener(new FirstListener());
//		tomcat.getHost().addChild(context);
//		tomcat.addServlet("/async", "asyncServlet", new AsyncServlet());
//		context.addServletMapping("/home", "asyncServlet");
//		tomcat.start();
//		tomcat.getServer().await();
		 String webappDirLocation = "D:\\webapp";
	        Tomcat tomcat = new Tomcat();


	        tomcat.setPort(8787);

	        StandardContext context = (StandardContext) tomcat.addWebapp("/async", webappDirLocation);
	        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

	        // Declare an alternative location for your "WEB-INF/classes" dir
	        // Servlet 3.0 annotation will work
//	        File additionWebInfClasses = new File("target/classes");
//	        WebResourceRoot resources = new StandardRoot(ctx);
//	        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
//	                additionWebInfClasses.getAbsolutePath(), "/"));
//	        ctx.setResources(resources);
	    	context.addApplicationLifecycleListener(new FirstListener());
			tomcat.addServlet("/async", "asyncServlet", new AsyncServlet());
			context.addServletMapping("/home", "asyncServlet");
	        tomcat.start();
	        tomcat.getServer().await();

	}
}
