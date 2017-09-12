package com.practice.java.collection;

import static org.junit.Assert.*;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class HashMapTest {
    
	
	/**
	 * HashMap中的一格如果数量超过8就会把链表变成红黑树
	 */
	
	static class OneHashEntity{

		@Override
		public int hashCode() {
			return 1;
		}
		
		
		
	}
	
	@Test
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		HashMap<OneHashEntity,Integer> map = new HashMap();
		
		for(Integer i = 0 ;i < 10 ; i++){
			OneHashEntity key = new OneHashEntity();
			map.put(key, i);
		}
		
		Field field = map.getClass().getDeclaredField("table");
		field.setAccessible(true);
		
		Map.Entry[] obj = (Entry[]) field.get(map);
		
		for(Map.Entry e : obj){
			System.out.println(null == e ? e :e.getClass());
		}
		
		System.out.println(map.size());
		
	}

}
