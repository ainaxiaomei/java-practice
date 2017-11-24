package com.practice.jmx;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class Start {
	public static void main(String[] args) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException, InterruptedException {
		ObjectName helloName = new ObjectName("jmxBean:name=hello");
		ManagementFactory.getPlatformMBeanServer().registerMBean(new Hello(), helloName);
		
		
		
		TimeUnit.MINUTES.sleep(5);
	}
}
