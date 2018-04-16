package com.practice.storm.window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.windowing.TupleWindow;

public class SliderWindowBolt extends BaseWindowedBolt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(TupleWindow inputWindow) {
		List<String> currWin = new ArrayList<>();
		List<String> expirWin = new ArrayList<>();
		List<String> newWin = new ArrayList<>();
		
		for(Tuple tuple : inputWindow.get()) {
			String str = tuple.getStringByField("window");
			currWin.add(str);
		}
		
		for(Tuple tuple : inputWindow.getNew()) {
			String str = tuple.getStringByField("window");
			newWin.add(str);
		}
		
		for(Tuple tuple : inputWindow.getExpired()) {
			String str = tuple.getStringByField("window");
			expirWin.add(str);
		}
		
		System.out.println(System.currentTimeMillis()+ " current windwow : " + Arrays.asList(currWin));
		System.out.println(System.currentTimeMillis()+ " expired windwow : " + Arrays.asList(expirWin));
		System.out.println(System.currentTimeMillis()+ " new windwow : " + Arrays.asList(newWin));
		
	}
	
	
	
	

}
