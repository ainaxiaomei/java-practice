package com.practice.storm.parallelism;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;


/**
 * 设置bolt的paralleslim为4，同时设置setTask为1。实际运行任务的数量为1,只会出现一个task
 * 设置bolt的paralleslim为1，同时设置setTask为4。实际运行任务的数量为4,都在同一个线程中
 * @author win
 *
 */
public class Topology {
	
	public static void main(String[] args) {
		
		TopologyBuilder builder = new TopologyBuilder();
		
		builder.setSpout("sample-spout", new SampleSpout());
		builder.setBolt("sample-bolt", new SampleBolt(),4)
		.shuffleGrouping("sample-spout")
		.setNumTasks(1);
//		builder.setBolt("sample-bolt", new SampleBolt(),1)
//		.shuffleGrouping("sample-spout")
//		.setNumTasks(4);
		StormTopology top = builder.createTopology();
		
		LocalCluster cluster = new LocalCluster();
		Config conf = new Config();
		
		//设置worker的数量为1
		Config.setNumWorkers(conf, 1);
		cluster.submitTopology("parallelism-topology", conf, top);
		
		
	}

}
