package com.practice.storm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;


public class Topology {
	
	public static void main(String[] args) throws InterruptedException {
		
		TopologyBuilder build = new TopologyBuilder();
		build.setSpout("WordReadSpout", new WordReadSpout());
		build.setBolt("WordNormalizerBolt", new WordNormalizerBolt()).shuffleGrouping("WordReadSpout");
		build.setBolt("WordCountBolt", new WordCountBolt())
		.fieldsGrouping("WordNormalizerBolt", new Fields("word"));
		
		StormTopology topology = build.createTopology();
		
		LocalCluster cluster = new LocalCluster();
		
		Config config = new Config();
		config.put("file", "word");
		cluster.submitTopology("wordcount", config, topology);
		
		Thread.sleep(5000);
		//手动关闭
		cluster.shutdown();
	}
	
	static class WordCountBolt implements IRichBolt{
		
		private OutputCollector collector;
		
		private Map <String,Integer> map = new HashMap<>();

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			System.out.println("prepare");
			this.collector = collector;
			
		}

		@Override
		public void execute(Tuple input) {
			String word = input.getStringByField("word");
			if(map.containsKey(word)){
				int count = map.get(word) + 1;
				map.put(word, count);
			}else{
				map.put(word, 1);
			}
			
			collector.ack(input);
		}

		@Override
		public void cleanup() {
			System.out.println("cleanup");
			map.entrySet().forEach((entity)->{
				System.out.println(entity.getKey() + ":" + entity.getValue());
			});
			
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			System.out.println("declarer");
			
		}

		@Override
		public Map<String, Object> getComponentConfiguration() {
			System.out.println("getComponentConfiguration");
			return null;
		}
		
	}
	
	static class WordNormalizerBolt implements IRichBolt{
		
		private OutputCollector collector;
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			System.out.println("prepare");
			this.collector =  collector;
		}

		@Override
		public void execute(Tuple input) {
			System.out.println("execute");
			
			String line = input.getStringByField("line");
			StringTokenizer token = new StringTokenizer(line);
			
			while(token.hasMoreTokens()){
				String str = token.nextToken().toLowerCase();
				collector.emit(new Values(str));
			}
			
			collector.ack(input);
			
		}

		@Override
		public void cleanup() {
			System.out.println("cleanup");
			
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			System.out.println("declareOutputFields");
			declarer.declare(new Fields("word"));
		}

		@Override
		public Map<String, Object> getComponentConfiguration() {
			System.out.println("getComponentConfiguration");
			return null;
		}
		
	}
	
	static class WordReadSpout implements IRichSpout{
		
		private SpoutOutputCollector outPut;
		
		private BufferedReader reader;

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
			System.out.println("open !");
			outPut = collector;
			String file = (String) conf.get("file");
			reader = new BufferedReader(new InputStreamReader(WordReadSpout.class.getClassLoader().getResourceAsStream(file)));
			
		}

		@Override
		public void close() {
			System.out.println("close");
			
		}

		@Override
		public void activate() {
			System.out.println("activate");
		}

		@Override
		public void deactivate() {
			System.out.println("deactivate");
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
			System.out.println(msgId + "ack !");
			
		}

		@Override
		public void fail(Object msgId) {
			System.out.println(msgId + "fail !");
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			
			System.out.println("declareOutputFields");
			declarer.declare(new Fields("line"));
			
		}

		@Override
		public Map<String, Object> getComponentConfiguration() {
			System.out.println("getComponentConfiguration");
			return null;
		}
		
	}

}
