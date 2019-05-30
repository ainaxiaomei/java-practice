package com.practice.jni;

public class JNIHelloWorld {
	
	static {
		System.loadLibrary("fping");
	}
	
	 public JNIHelloWorld() {
		 
	}
	
	
	native void fping(int argc,String[] args);
	
	public static void main(String[] args) {
		
		JNIHelloWorld jni = new JNIHelloWorld();
		
		jni.fping(args.length, args);
		
		
		
	}

}
