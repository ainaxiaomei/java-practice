package com.practice.java.reflect;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sun.reflect.CallerSensitive;

public class ReflectTest {
	


	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		 System.out.println(Mylist.class.getGenericSuperclass());
		 ParameterizedType ptype = (ParameterizedType) Mylist.class.getGenericSuperclass();
		 System.out.println(ptype.getActualTypeArguments()[0]);
		 
		 
		 System.out.println(Arrays.toString(GC.class.getTypeParameters()));
		 
		
	}
	
	
	
}

class  Mylist extends ArrayList<Integer> {

	private static final long serialVersionUID = 1L;
	
}

class GC<T> {
	
	public void print(T t) {
		
		System.out.println(t);
		
	}
	
}
 