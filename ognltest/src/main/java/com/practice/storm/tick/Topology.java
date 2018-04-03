package com.practice.storm.tick;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import com.practice.storm.streamId.RandomSpout;



/**
 * 测试strom的定时机制，通过重写bolt的getCompoment
 * @author win
 *
 */
public class Topology {
	
	public static void main(String[] args) throws InterruptedException {
		
		TopologyBuilder build = new TopologyBuilder();
		build.setSpout("tickSpout", new TickSpout());
		build.setBolt("tickBolt", new TickBolt()).fieldsGrouping("tickSpout", new Fields("tick"));
		
		StormTopology topology = build.createTopology();
		
		LocalCluster cluster = new LocalCluster();
		
		Config config = new Config();
		cluster.submitTopology("tick", config, topology);
		
	}
	

	
	

}
