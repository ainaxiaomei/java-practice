package com.practice.storm.wordcount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class WordReadSpout implements IRichSpout{
	private SpoutOutputCollector outPut;
	
	private BufferedReader reader;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		System.out.println("-- " + this.getClass() + " --> open");
		outPut = collector;
		String file = (String) conf.get("file");
		reader = new BufferedReader(new InputStreamReader(WordReadSpout.class.getClassLoader().getResourceAsStream(file)));
		
	}

	@Override
	public void close() {
		System.out.println("-- " + this.getClass() + " --> close");
		
	}

	@Override
	public void activate() {
		System.out.println("-- " + this.getClass() + " --> activate");
	}

	@Override
	public void deactivate() {
		System.out.println("-- " + this.getClass() + " --> deactivate");
	}

	@Override
	public void nextTuple() {
		String line = null;
		try {
			while((line = reader.readLine()) != null){
				outPut.emit(new Values(line));
			}
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void ack(Object msgId) {
		System.out.println("-- " + this.getClass() + " --> ack");
		
	}

	@Override
	public void fail(Object msgId) {
		System.out.println("-- " + this.getClass() + " --> fail");
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
		System.out.println("-- " + this.getClass() + " --> declareOutputFields");
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		System.out.println("-- " + this.getClass() + " --> getComponentConfiguration");
		return null;
	}
}
