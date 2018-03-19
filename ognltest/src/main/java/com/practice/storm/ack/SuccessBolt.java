package com.practice.storm.ack;

import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SuccessBolt extends BaseRichBolt  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		System.out.println("-- "+this.getClass().getName() +  " prepare");
		this.collector = collector;
	}
    
	
	
	/**
	 * 如果不锚定，当前节点是自动成为最后一个节点，ack触发spout ack,fail触发spout fail
	 * 如果锚定，ack不一定触发spout ack,fail触发spout fail
	 */
	@Override
	public void execute(Tuple input) {
		String line = input.getStringByField("line");
		System.out.println("-- "+this.getClass().getName() +  " execute -->" + input.getMessageId());
		
		//如果不使用锚定即任务tuple到此结束不会再往下追踪，此时ack则spout的ack会被调用
		//collector.emit(new Values(line + 1));
		collector.emit(input,new Values(line + 1));
		collector.ack(input);
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("result"));
		
	}


}
