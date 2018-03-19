package com.practice.storm.ack;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;



public class Topology {
	
	public static void main(String[] args) throws InterruptedException {
		
		TopologyBuilder build = new TopologyBuilder();
		build.setSpout("failspout", new FailSpout());
		build.setBolt("successbolt", new SuccessBolt()).localOrShuffleGrouping("failspout");
		build.setBolt("falibolt", new FailBolt()).localOrShuffleGrouping("successbolt");
		
		StormTopology topology = build.createTopology();
		
		LocalCluster cluster = new LocalCluster();
		
		cluster.submitTopology("fail", new Config(), topology);
		
		Thread.sleep(5000);
		//手动关闭
		cluster.shutdown();
	}
	

	
	

}
