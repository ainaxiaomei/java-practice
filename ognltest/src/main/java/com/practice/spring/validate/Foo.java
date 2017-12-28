package com.practice.spring.validate;

import java.util.ArrayList;
import java.util.List;

public class Foo {
	
	private int age ;
	
	private String name;
	
	private List<String> friend = new ArrayList<String>();
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFriend() {
		return friend;
	}

	public void setFriend(List<String> friend) {
		this.friend = friend;
	}

	@Override
	public String toString() {
		return "Foo [age=" + age + ", name=" + name + ", friend=" + friend + "]";
	}


	
	
	
	

}
