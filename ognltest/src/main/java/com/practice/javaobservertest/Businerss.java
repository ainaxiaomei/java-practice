package com.practice.javaobservertest;

import java.util.Observable;

public class Businerss extends StartUpListener {

	public Businerss(Observable o) {
		super(o);
	}

	@Override
	public void onStart(Observable o, Object arg) {
		System.out.println("observer containert stat arg is " + arg);
	}
	
	

}
