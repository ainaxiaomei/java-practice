package com.practice.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {

	static class ProbeInvocationHandler implements InvocationHandler{
		
		private ProbeImpl probe;

		
		public ProbeInvocationHandler(ProbeImpl probe) {
			super();
			this.probe = probe;
		}


		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("before inoke...");
			 method.invoke(probe, args);
			System.out.println("after inoke...");
			return "hello";
		}
		
	}

	public static void main(String[] args) {
		ProbeInvocationHandler in = new ProbeInvocationHandler(new ProbeImpl());
		Probe p = (Probe) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Probe.class},in);
		String ret = p.getProbeData();
		System.out.println(ret);
	}

}

interface Probe {
	public String getProbeData();
}


