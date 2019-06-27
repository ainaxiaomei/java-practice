package com.practice.storm.wordcount;

import java.util.Map;
import java.util.StringTokenizer;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class WordNormalizerBolt implements IRichBolt {

	private OutputCollector collector;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		System.out.println("-- " + this.getClass() + " --> prepare");
		this.collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		System.out.println("-- " + this.getClass() + " --> execute");

		String line = input.getStringByField("line");
		System.out.println("-- " + this.getClass() + " --> get intput :" + line);

		StringTokenizer token = new StringTokenizer(line);

		while (token.hasMoreTokens()) {
			String str = token.nextToken().toLowerCase();
			collector.emit(new Values(str));
		}

		collector.ack(input);

	}

	@Override
	public void cleanup() {
		System.out.println("-- " + this.getClass() + " --> cleanup");

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		System.out.println("-- " + this.getClass() + " --> declarer");
		declarer.declare(new Fields("word"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		System.out.println("-- " + this.getClass() + " --> getComponentConfiguration");
		return null;
	}

}