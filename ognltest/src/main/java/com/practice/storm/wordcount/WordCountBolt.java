package com.practice.storm.wordcount;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

public class WordCountBolt implements IRichBolt{
		
		private OutputCollector collector;
		
		private Map <String,Integer> map = new HashMap<>();

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
			System.out.println("-- " + this.getClass() + " --> cleanup");
			map.entrySet().forEach((entity)->{
				System.out.println(entity.getKey() + ":" + entity.getValue());
			});
			
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			System.out.println("-- " + this.getClass() + " --> declareOutputFields");
			
		}

		@Override
		public Map<String, Object> getComponentConfiguration() {
			System.out.println("-- " + this.getClass() + " --> getComponentConfiguration");
			return null;
		}
		
	}