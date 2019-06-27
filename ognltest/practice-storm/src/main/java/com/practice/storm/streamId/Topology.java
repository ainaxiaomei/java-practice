package com.practice.storm.streamId;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

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
