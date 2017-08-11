package com.practice.java.pattern;

import java.util.Observable;

public class Container extends Observable {
	
	public void start(){
		System.out.println("container start ...");
		setChanged();
		notifyObservers("start");
	}
	
	public static void main(String[] args) {
		Container c  = new Container();
		Businerss b = new Businerss(c);
		c.start();
	}
}
