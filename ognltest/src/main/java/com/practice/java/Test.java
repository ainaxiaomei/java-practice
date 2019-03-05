package com.practice.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
//		ExecutorService service = Executors.newCachedThreadPool();
//		service.submit(()->{
//			while(true) {
//				
//			}
//		});
		
		
		System.setSecurityManager(new SecurityManager());
		
		AccessController.doPrivileged(new PrivilegedAction<String>() {

			@Override
			public String run() {
				try {
					FileInputStream fis = new FileInputStream("./1");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return "";
			}
		});
		
		
		System.out.println(System.getSecurityManager());
//		new Thread(()->{
//			while(true) {
//				
//			}
//		}) {}.start();
//		System.out.println("exit");
	}
}
