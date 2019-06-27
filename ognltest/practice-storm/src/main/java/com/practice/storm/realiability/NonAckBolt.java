package com.practice.storm.realiability;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

public class NonAckBolt extends BaseRichBolt {
	
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
       System.out.println("-- NonAckBolt Rec :" + input.getStringByField("realiability"));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

}
