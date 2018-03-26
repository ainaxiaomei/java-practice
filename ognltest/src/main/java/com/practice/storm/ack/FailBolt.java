package com.practice.storm.ack;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

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
