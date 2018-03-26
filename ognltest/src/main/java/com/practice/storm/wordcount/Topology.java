package com.practice.storm.wordcount;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;



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
