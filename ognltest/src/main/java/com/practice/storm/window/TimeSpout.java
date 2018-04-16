package com.practice.storm.window;

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
 * TupleWindow记录了三个list,分别是当前窗口的tuple,过期的tuple,新到的tuple
 * @author win
 *
 */
public class TimeSpout extends BaseRichSpout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2796734860691189916L;
	private SpoutOutputCollector collector;
	private Random ran = new Random();
	

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void nextTuple() {
	    
	    String id = UUID.randomUUID().toString();
	    try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	    collector.emit(new Values(String.valueOf(System.currentTimeMillis())),id);

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("window"));
	}

	/**
	 * 测试windowbolt是否自动锚定
	 */
	@Override
	public void ack(Object msgId) {
		
		System.out.println("-- acked : " + msgId);
	}
	
	

}
