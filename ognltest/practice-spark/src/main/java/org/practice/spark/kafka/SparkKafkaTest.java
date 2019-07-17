package org.practice.spark.kafka;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import kafka.serializer.StringDecoder;


public class SparkKafkaTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		SparkConf sparkConf = new SparkConf();
		sparkConf.setAppName("probe");
		sparkConf.setMaster("local");
		JavaStreamingContext jsc = new JavaStreamingContext(
		  sparkConf, Durations.seconds(5));
		
		Map<String, String> kafkaParams = new HashMap<>();
		kafkaParams.put("bootstrap.servers", "192.168.2.3:9092");
		Set<String> topics = new HashSet<>(Arrays.asList("kafka-probe"));
		 
		JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(
				jsc,
		        String.class,
		        String.class,
		        StringDecoder.class,
		        StringDecoder.class,
		        kafkaParams,
		        topics
		    );
		
		
		messages.map((t) -> {
			
			ObjectMapper om = new ObjectMapper();
			Probe p = om.readValue(t._2,Probe.class);
			return p;
		}).print();
		
		jsc.start();
		jsc.awaitTermination();
		
	}
	
	
	public static class Probe {
		
		private String prober_total_time;

		public String getProber_total_time() {
			return prober_total_time;
		}

		public void setProber_total_time(String prober_total_time) {
			this.prober_total_time = prober_total_time;
		}
		
	}

}
