package com.practice.storm.streamId;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

public class StartNonABolt extends BaseRichBolt {
	
	private OutputCollector collector ;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		
	}

	@Override
	public void execute(Tuple input) {
		
		System.out.println("-- StartNonABolt :" + input.getStringByField("word"));

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

}
