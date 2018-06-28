package com.practice.storm.ack;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.scheduler.Cluster;
import org.apache.storm.topology.TopologyBuilder;

public class Topology {
	
	public static void main(String[] args) throws InterruptedException {
		
		TopologyBuilder build = new TopologyBuilder();
		build.setSpout("failspout", new FailSpout());
		build.setBolt("successbolt", new SuccessBolt()).localOrShuffleGrouping("failspout");
		build.setBolt("falibolt", new FailBolt()).localOrShuffleGrouping("successbolt");
		
		StormTopology topology = build.createTopology();
		
		LocalCluster cluster = new LocalCluster();
		
		cluster.submitTopology("fail", new Config(), topology);
		
		
	}
	

	
	

}
