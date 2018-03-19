package com.practice.storm.ack;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class FailBolt extends BaseRichBolt  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		System.out.println("-- "+this.getClass().getName() +  " prepare");
		this.collector = collector;
	}
    
	
	@Override
	public void execute(Tuple input) {
		System.out.println("-- "+this.getClass().getName() +  " execute -->" + input.getMessageId());
		String line = input.getStringByField("result");
		collector.fail(input);
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}


}
