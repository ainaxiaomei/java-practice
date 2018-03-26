package com.practice.storm.streamId;

import java.util.Map;
import java.util.Random;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

/**
 * 1.spout bolt在绑定的时候可以指定steamId，在spout或者bolt向下游
 * 发送的时候指定streamid，tuple只会根据streamid路由到不同节点
 * 2.默认不指定时，绑定和发送的stramid都是default 
 * 3.streamid也需要声明
 * @author vergil
 *
 */
public class RandomSpout extends BaseRichSpout {
	
	private SpoutOutputCollector collctor;
	
	private Random ran = new Random();

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collctor = collector;
	}

	@Override
	public void nextTuple() {
		
		String[] src = new String[] {"Abc","about","bce","dsf","sfds","hello","abey"};
	    int index = ran.nextInt(100)%src.length;
	    
	    if(src[index].startsWith("A")||src[index].startsWith("a")) {
	    	collctor.emit("startA",new Values(src[index]));
	    }else {
	    	collctor.emit("startNonA",new Values(src[index]));
	    }
	    
	    
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream("startA", new Fields("word"));
		declarer.declareStream("startNonA",  new Fields("word"));

	}

}
