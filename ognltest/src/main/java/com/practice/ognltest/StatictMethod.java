package com.practice.ognltest;

public class StatictMethod {
	
	private int count;
	
	@FunctionalInterface
	public interface Print{
		public int PrintInt(String value);
	}
	
	public int getCount(String value){
		return count;
		
	}
	
	
	public StatictMethod(int count) {
		super();
		this.count = count;
	}


	public static void main(String[] args) {
		Print p = Integer::valueOf;
		System.out.println(p.PrintInt("10"));
		
		StatictMethod sm = new StatictMethod(1);
		Print p2 = sm::getCount;
		System.out.println( p2.PrintInt("10"));
		
		StatictMethod sm2 = new StatictMethod(3);
		Print p3 = sm2::getCount;
		System.out.println( p3.PrintInt("10"));
	}
}
