package com.practice.ognltest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTest {
   
	public static void main(String[] args) throws CloneNotSupportedException {
//		String a[] = new String[10]; 
//		a[0] = "hello";
//		
//		String b[] = a;
//		b[0] = "world";
		//System.out.println(Arrays.toString(a));
		//System.out.println(Arrays.toString(b));
		
		Clo clo = new Clo("123", new ArrayList<String>(){{add("456");}}, 1);
		
		Clo clob = (Clo) clo.clone();
		
		System.out.println(clo.getA()==clob.getA());
		System.out.println(clo.getStr()==clob.getStr());
		System.out.println(clo + " " + clob);
		
		
	}
	
}

class Clo implements Cloneable{
	private String a;
	
	private List<String> str;
	
	private int b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public List<String> getStr() {
		return str;
	}

	public void setStr(List<String> str) {
		this.str = str;
	}

	public Clo(String a, List<String> str, int b) {
		super();
		this.a = a;
		this.str = str;
		this.b = b;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "Clo [a=" + a + ", str=" + str + ", b=" + b + "]";
	}
	
	
	
	
}
