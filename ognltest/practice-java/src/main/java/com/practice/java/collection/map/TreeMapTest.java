package com.practice.java.collection.map;

import java.util.NavigableMap;
import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTest {

	@Test
	public void test() {
		
		TreeMap<Integer,String> tree = new TreeMap<>();
		
		tree.put(10, "1");
		tree.put(1, "1");
		tree.put(70, "1");
		tree.put(6, "1");
		tree.put(100, "1");
		tree.put(9, "1");
		tree.put(8, "1");
		
		System.out.println(tree.toString());
		System.out.println("floor 8 is " + tree.floorKey(8));
		System.out.println("ceil 8 is " + tree.ceilingKey(8));
		System.out.println("lower 8 is " + tree.lowerKey(8));
		System.out.println("higher 8 is " + tree.higherKey(8));
		
		System.out.println("firstEntry  is " + tree.firstEntry());
		System.out.println("pollFirstEntry  is " + tree.pollFirstEntry());
		System.out.println("firstEntry  is " + tree.firstEntry());
		
		System.out.println(tree.toString());
		
		NavigableMap<Integer, String> m = tree.descendingMap();
		tree.put(10, "100");
		System.out.println("descendingKeySet  is " +  m);
		
	}

}
