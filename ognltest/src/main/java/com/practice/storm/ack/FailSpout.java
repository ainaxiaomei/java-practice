package com.practice.storm.ack;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;


/**
 * 测试bolt失败后spout的处理
 * @author win
 *
 */
public class FailSpout extends BaseRichSpout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SpoutOutputCollector collector;
	
	private Random random;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		random = new Random(100);
	}

	@Override
	public void nextTuple() {
		
		String str = String.valueOf(random.nextInt(100));
		String id = UUID.randomUUID().toString();
		System.out.println("-- "+this.getClass().getName() +  " nextTuple -->" + id);
		collector.emit(new Values(str),id);
		while(true) {}
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}

	@Override
	public void ack(Object msgId) {
		
		System.out.println("-- "+this.getClass().getName() +  " ack -->" + msgId);
	}

	@Override
	public void fail(Object msgId) {
		System.out.println("-- "+this.getClass().getName() +  " fail -->" + msgId);
	}
	
	

	
	

}
