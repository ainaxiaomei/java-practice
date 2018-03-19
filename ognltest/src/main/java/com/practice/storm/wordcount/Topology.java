package com.practice.storm.wordcount;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;



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
	

	
	

}
