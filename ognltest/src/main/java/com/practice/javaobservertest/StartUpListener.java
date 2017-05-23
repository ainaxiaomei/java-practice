package com.practice.javaobservertest;

import java.util.Observable;
import java.util.Observer;

public abstract class  StartUpListener implements Observer {
	
	public StartUpListener(Observable o) {
		o.addObserver(this);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		onStart(o,arg);
		
	}


	public abstract void onStart(Observable o, Object arg);
}
