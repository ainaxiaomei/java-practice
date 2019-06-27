package com.practice.storm.window;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseWindowedBolt.Duration;

public class Topology {

	public static void main(String[] args) {
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("winSpout", new TimeSpout());
		builder.setBolt("win-bolt", new SliderWindowBolt()
				.withWindow(Duration.seconds(10), Duration.seconds(3)))
				.shuffleGrouping("winSpout");
		builder.setBolt("res-bolt", new ResultBolt()).globalGrouping("win-bolt");
		StormTopology topology = builder.createTopology();
		
		Config conf = new Config();
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("window-topology", conf, topology);

	}

}
