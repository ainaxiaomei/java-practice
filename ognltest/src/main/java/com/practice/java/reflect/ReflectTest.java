package com.practice.java.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sun.reflect.CallerSensitive;

public class ReflectTest {
	


	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		 System.out.println(Mylist.class.getGenericSuperclass());
		 ParameterizedType ptype = (ParameterizedType) Mylist.class.getGenericSuperclass();
		 System.out.println(ptype.getActualTypeArguments()[0] instanceof TypeVariable);
		 System.out.println("getTypeName " + ptype.getTypeName());
		 System.out.println("getOwnerType " + ptype.getOwnerType());
		 System.out.println("getRawType " + ptype.getRawType());
		 
		 
		 
		 
		 int a [] = new int[10];
		 System.out.println(a.getClass().getTypeName());
		 
		 System.out.println(Arrays.toString(GC.class.getTypeParameters()));
		 
		 System.out.println(ArrayList.class.getGenericSuperclass() instanceof ParameterizedType);
		 
		
	}
	
	
	
}

class  Mylist extends ArrayList<Integer> {

	private static final long serialVersionUID = 1L;
	
}

class GC<Integer> {
	
	public void print(Integer t) {
		
		System.out.println(t);
		
	}
	
}
 