package com.practice.storm.window;

import java.util.Map;
import java.util.Random;
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
public class RandomSpout extends BaseRichSpout {
	
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
		String[] src = new String[] {"111","222","333","444","555","666","777"};
	    int index = ran.nextInt(100)%src.length;
	    try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		};
	    collector.emit(new Values(src[index]));

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("window"));
	}

}
