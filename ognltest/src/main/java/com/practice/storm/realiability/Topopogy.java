package com.practice.storm.realiability;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

public class Topopogy {

	public static void main(String[] args) {
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("nonResend", new NonReSendSpout());
		builder.setBolt("nonAck", new NonAckBolt()).shuffleGrouping("nonResend");
		
		StormTopology topology = builder.createTopology();
		
		LocalCluster cluster = new LocalCluster();
		Config conf = new Config();
		conf.put(Config.TOPOLOGY_MESSAGE_TIMEOUT_SECS, 2);
		cluster.submitTopology("realiability-topology", conf, topology);

	}

}
