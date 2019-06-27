package com.practice.storm.parallelism;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

public class SampleBolt extends BaseRichBolt {
	
	private OutputCollector collector;
	
	private TopologyContext context;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		this.context = context;
	}

	@Override
	public void execute(Tuple input) {
		
		System.out.println("--- taksID : " + context.getThisTaskId());
		System.out.println("--- thread : " + Thread.currentThread());
		System.out.println("--- workerTasks : " + context.getThisWorkerTasks());
		System.out.println("--- ThisTaskIndex : " + context.getThisTaskIndex());
		collector.ack(input);

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

	}

}
