package com.practice.ognltest;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller{
	
	private List<Inteceptor> list = new ArrayList<Inteceptor>();
    private int current = -1;
	public ControllerImpl() {
		super();
		list.add(new InteceptorA());
		list.add(new InteceptorB());
	}

	public void process() {
		 ++current;
		 
		 if(current == list.size()){
		     System.out.println("do you work !");
 
		 }
		 
		 if(current <= list.size()-1){
			 list.get(current).doIntecpt(this);
		 }
		 
		 
		
		
	}
	
	public static void main(String[] args) {
		ControllerImpl control = new ControllerImpl();
		control.process();
	}

}
