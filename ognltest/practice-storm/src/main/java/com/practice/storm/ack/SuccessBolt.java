package com.practice.storm.ack;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class SuccessBolt extends BaseRichBolt  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	
	private TopologyContext context;

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
