package com.practice.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class JDKProxy2 {
	public static void main(String[] args) {
		ProInvocationHandler h = new ProInvocationHandler(new ProbeImpl());
		Probe p = (Probe)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Add.class,Probe.class}, h);
		Add a = (Add)p;
		
		a.addIntercept(()->{
			System.out.println("before1 ...");
		});
		a.addIntercept(()->{
			System.out.println("before2 ...");
		});
		a.addIntercept(()->{
			System.out.println("before3 ...");
		});
		a.addIntercept(()->{
			System.out.println("before4 ...");
		});
		p.getProbeData();
	}
}

class ProInvocationHandler implements InvocationHandler{
	

	private ProbeImpl probe;
	
	private Add defautAdd;
	
	public ProInvocationHandler(ProbeImpl probe) {
		super();
		this.probe = probe;
		defautAdd = new DefaultAddImpl();
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		if(method.getDeclaringClass().isAssignableFrom(Add.class)){
			return method.invoke(defautAdd, args);
		}
		
		defautAdd.getIntercept().parallelStream().forEach((item)->{
			item.dointercept();
		});
		method.invoke(probe, args);
		
		return null;
	}
	
	
}

class DefaultAddImpl implements Add{
	private List<Intercept> list;

	public DefaultAddImpl() {
		list = new ArrayList<>();
	}


	@Override
	public void addIntercept(Intercept in) {
		
		list.add(in);
		
	}


	@Override
	public List<Intercept> getIntercept() {
		return list;
	}
	
}

interface Add{
	void addIntercept(Intercept in);
	
	List<Intercept> getIntercept();
}

interface Intercept{
	void dointercept();
}

