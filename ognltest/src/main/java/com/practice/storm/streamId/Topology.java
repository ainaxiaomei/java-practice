package com.practice.storm.streamId;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class Topology {
	
	public static void main(String[] args) {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("randomSpout", new RandomSpout());
		builder.setBolt("startABolt", new StartABolt()).localOrShuffleGrouping("randomSpout","startA");
		builder.setBolt("startNonBolt", new StartNonABolt()).localOrShuffleGrouping("randomSpout","startNonA");
		StormTopology top = builder.createTopology();
		
		LocalCluster cluser = new LocalCluster();
		Config conf = new Config();
		cluser.submitTopology("randomSpout", conf, top);
	}

}
