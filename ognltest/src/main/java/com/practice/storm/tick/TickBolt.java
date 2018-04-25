package com.practice.storm.tick;

import java.util.Map;

import org.apache.storm.Config;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.utils.TupleUtils;

public class TickBolt extends BaseRichBolt{

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
		if(TupleUtils.isTick(input)) {
			System.out.println("-- tick tuple: " + System.currentTimeMillis());
		}else {
			System.out.println("-- regular tuple: " + input.getValueByField("tick"));
			collector.ack(input);
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		
		Config conf = new  Config();
		conf.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, 5);
		return conf;
	}
	
	

	
	

}
