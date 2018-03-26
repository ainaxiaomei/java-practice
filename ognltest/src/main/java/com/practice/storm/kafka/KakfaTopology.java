package com.practice.storm.kafka;

import java.util.UUID;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.topology.TopologyBuilder;



public class KakfaTopology {

	public static void main(String[] args) {
		
		TopologyBuilder builder = new TopologyBuilder();
		
		ZkHosts zkhosts = new ZkHosts("");
		SpoutConfig spoutConf = new SpoutConfig(zkhosts,"probe","/probe", UUID.randomUUID().toString());
		KafkaSpout kafkaSpout = new KafkaSpout(spoutConf);
		builder.setSpout("kafkaSpout", kafkaSpout);
		
		StormTopology topology = builder.createTopology();
		
		LocalCluster cluster = new LocalCluster();
		Config config = new Config();
		cluster.submitTopology("kafka-taks", config, topology);
	}

}
