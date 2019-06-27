package com.practice.storm.window;

import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

public class ResultBolt extends BaseRichBolt{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		List<String> list = (List)input.getValueByField("window");
		System.out.println("-- input :" + list);
		
		//collector.ack(input);
		System.out.println("-- Source Component : " + input.getSourceComponent());
	
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}


}
