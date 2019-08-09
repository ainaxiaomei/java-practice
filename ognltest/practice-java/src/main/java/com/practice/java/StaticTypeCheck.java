package com.practice.java;

public class StaticTypeCheck {

	public StaticTypeCheck getInstance() {
		return this;
	}

	public void DoA() {
		System.out.println("Do A");
	}
	
	public static void main(String[] args) {
		new StaticTypeCheckB().getInstance().DoA();
		//error 
		//new StaticTypeCheckB().getInstance().DoB();
		((StaticTypeCheckB)(new StaticTypeCheckB().getInstance())).DoB();
	}
}

class StaticTypeCheckB extends StaticTypeCheck {
	public void DoB() {
		System.out.println("Do B");
	}
}
