package com.practice.storm.window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseWindowedBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.apache.storm.windowing.TupleWindow;

public class SliderWindowBolt extends BaseWindowedBolt {
	
	private OutputCollector collector ;
	

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

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
		
		System.out.println(new Date()+ " current windwow : " + Arrays.asList(currWin));
		System.out.println(new Date()+ " expired windwow : " + Arrays.asList(expirWin));
		System.out.println(new Date()+ " new windwow : " + Arrays.asList(newWin));
		
		
		
		collector.emit(new Values(currWin));
		
	}
	
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("window"));
	}
	
	

}
