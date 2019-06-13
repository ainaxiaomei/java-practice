package org.practice.dubbo.springboot;

public class FpingTest{
	
	static {
		System.loadLibrary("fping");
	}
	
	
	
	public native void fping(int argc,String[] args);


	public static void main(String[] args) {
		
		FpingTest f = new FpingTest();
		
		System.out.println(" --- 第一次 " );
		f.fping(args.length,args);
		
		System.out.println(" --- 第二次 " );
		f.fping(args.length,args);
		
		
		
	}

}
