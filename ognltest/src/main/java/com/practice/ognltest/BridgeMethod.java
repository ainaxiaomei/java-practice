package com.practice.ognltest;

public class BridgeMethod {
	public static class Gen<E>{
		public void put(E el){
			
		}
	}
	
	public static class InGen extends Gen<Integer>{

		@Override
		public void put(Integer el) {
			
		}
		
	}
	
	public static void main(String[] args) {
		Gen gen = new InGen();
		gen.put("123");//报错
	}
}
