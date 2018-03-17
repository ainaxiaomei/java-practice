package com.practice.storm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBatchBolt;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;


/**
 * 即使没有bolt,spout也会执行
 * @author win
 *
 */
public class Test {
	public static void main(String[] args) {
		TopologyBuilder builder = new TopologyBuilder();
		
		builder.setSpout("wordSpout", new WordSpout());
		builder.setBolt("wordupperbolt", new WorderUpperBolt()).localOrShuffleGrouping("wordSpout");
		StormTopology topology = builder.createTopology();
		
		LocalCluster cluster = new LocalCluster();
		Map map = new HashMap<>();
		cluster.submitTopology("word-count", map, topology);
		
	}
	
	
	static class WorderUpperBolt implements IRichBatchBolt{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private OutputCollector collector;

		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			this.collector = collector;
		}

		@Override
		public void execute(Tuple input) {
			
			
			String word = input.getStringByField("word");
			System.out.println("WorderUpperSpout get input :" + word);
			Values values = new Values(word.toUpperCase());
			System.out.println("1231231");
			collector.emit(values);
			
			
		}

		@Override
		public void cleanup() {
			
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields("word"));
		}

		@Override
		public Map<String, Object> getComponentConfiguration() {
			return null;
		}
		
	}
	
	static class WordSpout implements IRichSpout{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private SpoutOutputCollector collector ;

		@Override
		public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
			this.collector = collector;
		}

		@Override
		public void close() {
			
		}

		@Override
		public void activate() {
			
		}

		@Override
		public void deactivate() {
			
		}

		@Override
		public void nextTuple() {
			
		    Values values = new Values();
		    
		    String[] str = new String[] {"hello","world","tuple","spout"};
		    
		    Random radom = new Random();
		    String value = str[radom.nextInt(str.length)];
		    System.out.println("word spout emit : " + value);
		    values.add(value);
			collector.emit(values);
			
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void ack(Object msgId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void fail(Object msgId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields("word"));
		}

		@Override
		public Map<String, Object> getComponentConfiguration() {
			return null;
		}
		
	}
}
