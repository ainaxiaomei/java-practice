package com.practice.java.collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

/**
 * set集合没有get方法，HashSet的实现依靠HashMap
 * @author Administrator
 *
 */
public class SetTest{
	
	/**
	 * HashSet迭代顺序与插入顺序不一致
	 */
	@Test
	public void hashSetTest(){
		Set<String> set = new HashSet<String>();
		set.add("1eer");
		set.add("2ee");
		set.add("3e");
		set.add("4ee");
		set.add("5");
		set.add("6xzf");
		set.add("7w");
		set.add("8xcc");
		set.add("9xxx");
		System.out.println(set);
	}
	
	/**
	 * LinkedHashSet迭代顺序与插入顺序一致
	 */
	@Test
	public void linkedashSetTest(){
		Set<String> set = new LinkedHashSet<String>();
		set.add("1eer");
		set.add("2ee");
		set.add("3e");
		set.add("9xxx");
		set.add("4ee");
		set.add("5");
		set.add("6xzf");
		set.add("7w");
		set.add("8xcc");
		set.add("9xxx");
		System.out.println(set);
	}
}
