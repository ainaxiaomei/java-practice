package com.practice.storm.streamId;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class StartABolt extends BaseRichBolt {
	
	private OutputCollector collector ;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		
	}

	@Override
	public void execute(Tuple input) {
		
		System.out.println("-- StartABolt :" + input.getStringByField("word"));

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

}
