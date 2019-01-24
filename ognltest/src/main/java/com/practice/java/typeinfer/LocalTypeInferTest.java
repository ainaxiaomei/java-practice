package com.practice.java.typeinfer;

import java.util.ArrayList;
import java.util.List;

public class LocalTypeInferTest {
	
	public static void main(String[] args) {
		
		
		List<Object> ls = null;
		
		for (Object obj : ls ) {
			
			obj.toString();
			
		}
	}

}
