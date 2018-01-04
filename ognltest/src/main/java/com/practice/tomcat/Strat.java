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
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
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
		String webappDirLocation = "G:\\VirtualStore";
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8787);
		StandardContext context = (StandardContext) tomcat.addWebapp("/javaTest", webappDirLocation);
		System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

		context.addApplicationLifecycleListener(new FirstListener());
		
		
		/**
		 * java EE的安全机制使用编程方式实现，在jesery中不会经过过滤器包括前置过滤器
		 */
//		SecurityConstraint constraint = new SecurityConstraint();
//		
//		SecurityCollection collection = new SecurityCollection();
//		collection.addPattern("/jersey/async/*");
//		constraint.addCollection(collection);
//		
//		constraint.addAuthRole("sunqi");
//		context.addConstraint(constraint);
		
		/**
		 * 设置认证机制为
		 */
//		LoginConfig config = new LoginConfig();
//		config.setAuthMethod("BASIC");
//		context.setLoginConfig(config);
		
		
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
		 * 编程式的注册
		 */
		
		wrapper.addInitParameter("javax.ws.rs.Application", "com.practice.tomcat.JerseyApplication");
		
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
