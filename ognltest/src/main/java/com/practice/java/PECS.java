package com.practice.java;

import java.util.ArrayList;
import java.util.List;

public class PECS {

	public static class Fruit{
		
	}
	
	public static class Apple extends Fruit{
		
	}
	
	public static class Orange extends Fruit{
		
	}
	
	public static void get(List<?> ls){
		ls.get(0);
		//ls.add(1, 1);
	}
	
	public static void getExtends(List<? extends Fruit> ls){
		ls.get(0);
		//ls.add(1, 1);
	}
	
	public static <T> void addSuper(List<? super T> ls){
		System.out.println(ls.get(0));
	}
	
	public static void main(String[] args) {
		List<Apple> ls = new ArrayList<>();
		PECS.addSuper(ls);
	}
}

