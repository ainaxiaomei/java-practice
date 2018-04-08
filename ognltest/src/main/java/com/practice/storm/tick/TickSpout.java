package com.practice.storm.tick;

import java.util.Map;
import java.util.Random;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;


public class TickSpout extends BaseRichSpout {
	
	private SpoutOutputCollector collector;
	
	private Random ran = new Random();

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void nextTuple() {

		String[] src = new String[] {"111","222","333","444","555","666","777"};
	    int index = ran.nextInt(100)%src.length;
	    
	    collector.emit(new Values(src[index]));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("tick"));
	}

}
