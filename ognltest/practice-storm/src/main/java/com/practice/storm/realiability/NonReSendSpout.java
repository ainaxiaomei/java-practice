package com.practice.storm.realiability;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * 普通的spout超时不会重发
 * @author vergil
 *
 */
public class NonReSendSpout extends BaseRichSpout {
	
	private SpoutOutputCollector collector;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	@Override
	public void nextTuple() {
		collector.emit(new Values(new Date() + " ：Hi"),UUID.randomUUID().toString());
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("realiability"));
	}

	@Override
	public void ack(Object msgId) {
		
		System.out.println("-- ack : " + msgId);
	}

	@Override
	public void fail(Object msgId) {
		
		System.out.println("-- " + new Date() + " fail : " + msgId);
	}
    
	
}
