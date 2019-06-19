package org.practice.spring.validate;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Foo {
	
	@Min(value=1,message="年龄最小为1")
	private int age ;
	
	private String name;
	@Size(max=1,message="最多一个好友")
	@FriendConstraint(message="名称必须以A开头")
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
